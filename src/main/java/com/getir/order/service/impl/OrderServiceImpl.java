package com.getir.order.service.impl;

import com.getir.order.adapter.OrderAdapter;
import com.getir.order.adapter.OrderDetailAdapter;
import com.getir.order.constant.OrderStatus;
import com.getir.order.dto.BookDTO;
import com.getir.order.dto.OrderDTO;
import com.getir.order.dto.OrderDetailDTO;
import com.getir.order.dto.SoldBookDTO;
import com.getir.order.exception.BookListNotUpdatedException;
import com.getir.order.exception.DateFormatIsNotValidException;
import com.getir.order.exception.NoOrderFoundInGivenDateRangeException;
import com.getir.order.exception.OrderNotFoundByIdException;
import com.getir.order.model.Order;
import com.getir.order.model.OrderDetail;
import com.getir.order.repository.OrderRepository;
import com.getir.order.service.BookClientService;
import com.getir.order.service.OrderDetailService;
import com.getir.order.service.OrderService;
import com.getir.order.service.SequenceService;
import com.getir.order.util.DateTimeUtil;
import com.getir.order.util.DateValidator;
import com.getir.order.util.OrderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final SequenceService sequenceService;
    private final BookClientService bookClientService;
    private final OrderDetailService orderDetailService;
    private final OrderDetailAdapter orderDetailAdapter;
    private final OrderAdapter orderAdapter;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {RuntimeException.class})
    public OrderDTO saveNewOrder(OrderDTO orderDTO) throws RuntimeException {
        OrderDTO newOrderDTO = OrderDTO.builder().build();

        if (Objects.nonNull(orderDTO) && !CollectionUtils.isEmpty(orderDTO.getOrderDetailDTOList())) {
            //Check book stock info and calculate total prices start
            List<Long> bookIdList = orderDTO.getOrderDetailDTOList().stream().map(OrderDetailDTO::getBookId).collect(Collectors.toList());
            List<BookDTO> bookDTOList = bookClientService.getBookListByIds(bookIdList);

            orderDTO = OrderUtil.checkStockDetails(orderDTO, bookDTOList);
            //Check book stock info and calculate total prices end

            //Insert order and order details start
            Order order = Order.builder().customerId(orderDTO.getCustomerId()).orderTime(LocalDateTime.now()).orderStatus(OrderStatus.ACTIVE).totalOrderPrice(orderDTO.getTotalOrderPrice()).build();
            order.setId(sequenceService.getSequenceNumber(Order.SEQUENCE_NAME));
            Order newOrder = orderRepository.save(order);
            List<OrderDetail> newOrderDetailList = orderDetailService.saveAllOrderDetails(orderDetailAdapter.prepareOrderDetailList(newOrder, orderDTO.getOrderDetailDTOList()));

            newOrderDTO = orderAdapter.prepareOrderDto(newOrder, orderDetailAdapter.prepareOrderDetailDtoList(newOrderDetailList));
            //Insert order and order details end

            //Update stock information after placing order start
            List<SoldBookDTO> soldBookDTOList = new ArrayList<>();
            for (OrderDetailDTO od : orderDTO.getOrderDetailDTOList()) {
                soldBookDTOList.add(new SoldBookDTO(od.getBookId(), od.getCount()));
            }
            try {
                bookClientService.updateStockInfo(soldBookDTOList);
            } catch (Exception e) {
                throw new BookListNotUpdatedException();
            }
            //Update stock information after placing order end
        }
        return newOrderDTO;
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundByIdException::new);
        OrderDTO orderDTO = OrderDTO.builder().build();
        BeanUtils.copyProperties(order, orderDTO);
        orderDTO.setOrderDetailDTOList(orderDetailAdapter.prepareOrderDetailDtoList(orderDetailService.findOrderDetailListByOrderId(order.getId())));
        return orderDTO;
    }

    @Override
    public List<Order> getOrderListByDateRange(String startDate, String endDate) {
        if (!DateValidator.isDateStringValid(startDate) || !DateValidator.isDateStringValid(endDate))
            throw new DateFormatIsNotValidException();
        LocalDateTime startLocalDateTime = DateTimeUtil.convertToLocalDateTime(startDate);
        LocalDateTime endLocalDateTime = DateTimeUtil.convertToLocalDateTime(endDate);
        List<Order> orderList = orderRepository.findOrdersByOrderTimeBetween(startLocalDateTime, endLocalDateTime);
        if (CollectionUtils.isEmpty(orderList))
            throw new NoOrderFoundInGivenDateRangeException();
        return orderList;
    }

    @Override
    public List<Order> getOrderListByCustomer(Long id) {
        return orderRepository.findOrdersByCustomerId(id);
    }

    @Override
    public List<OrderDTO> getOrderDTOList(List<Order> orderList) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orderList) {
            OrderDTO orderDTO = OrderDTO.builder().build();
            BeanUtils.copyProperties(order, orderDTO);
            orderDTO.setOrderDetailDTOList(orderDetailAdapter.prepareOrderDetailDtoList(orderDetailService.findOrderDetailListByOrderId(order.getId())));
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }


}
