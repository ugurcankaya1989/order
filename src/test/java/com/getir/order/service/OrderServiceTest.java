package com.getir.order.service;

import com.getir.order.adapter.OrderAdapter;
import com.getir.order.adapter.OrderDetailAdapter;
import com.getir.order.constant.OrderStatus;
import com.getir.order.dto.BookDTO;
import com.getir.order.dto.OrderDTO;
import com.getir.order.dto.OrderDetailDTO;
import com.getir.order.exception.OrderNotFoundByIdException;
import com.getir.order.model.Order;
import com.getir.order.model.OrderDetail;
import com.getir.order.repository.OrderRepository;
import com.getir.order.service.impl.OrderServiceImpl;
import com.getir.order.util.DateTimeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private SequenceService sequenceService;
    @Mock
    private BookClientService bookClientService;
    @Mock
    private OrderDetailService orderDetailService;
    @Mock
    private OrderDetailAdapter orderDetailAdapter;
    @Mock
    private OrderAdapter orderAdapter;

    @Test
    void getOrderById_test() {
        Order order = Order.builder().id(1l).customerId(2l).orderTime(LocalDateTime.now()).orderStatus(OrderStatus.ACTIVE).totalOrderPrice(new BigDecimal(20)).build();
        List<OrderDetail> orderDetailList = Arrays.
                asList(OrderDetail.builder().orderId(1l).id(1l).bookId(3l).count(10).totalPrice(new BigDecimal(15l)).build(),
                        OrderDetail.builder().orderId(1l).id(2l).bookId(2l).count(20).totalPrice(new BigDecimal(5l)).build());
        List<OrderDetailDTO> orderDetailDTOList = Arrays.
                asList(OrderDetailDTO.builder().orderId(1l).id(1l).bookId(3l).count(10).totalPrice(new BigDecimal(15l)).build(),
                        OrderDetailDTO.builder().orderId(1l).id(2l).bookId(2l).count(20).totalPrice(new BigDecimal(5l)).build());
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        when(orderDetailService.findOrderDetailListByOrderId(order.getId())).thenReturn(orderDetailList);
        when(orderDetailAdapter.prepareOrderDetailDtoList(orderDetailList)).thenReturn(orderDetailDTOList);

        OrderDTO orderDTO = orderService.getOrderById(1l);

        assertThat(orderDTO).isNotNull();
        assertEquals(2l, orderDTO.getCustomerId());

    }

    @Test
    void should_throw_exception_when_order_not_found() {
        when(orderRepository.findById(anyLong())).thenThrow(OrderNotFoundByIdException.class);
        assertThrows(OrderNotFoundByIdException.class, () -> {
            orderService.getOrderById(anyLong());
        });
    }

    @Test
    void getOrderListByCustomer_test() {
        Order order1 = Order.builder().id(1l).customerId(2l).orderTime(LocalDateTime.now()).orderStatus(OrderStatus.ACTIVE).totalOrderPrice(new BigDecimal(20)).build();
        Order order2 = Order.builder().id(2l).customerId(2l).orderTime(LocalDateTime.now()).orderStatus(OrderStatus.ACTIVE).totalOrderPrice(new BigDecimal(20)).build();
        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);
        when(orderRepository.findOrdersByCustomerId(2l)).thenReturn(orderList);
        List<Order> receivedOrderList = orderService.getOrderListByCustomer(2l);
        assertThat(receivedOrderList).isNotEmpty();
        assertEquals(2, receivedOrderList.size());
    }

    @Test
    void getOrderDTOList_test() {
        Order order = Order.builder().id(1l).customerId(2l).orderTime(LocalDateTime.now()).orderStatus(OrderStatus.ACTIVE).totalOrderPrice(new BigDecimal(20)).build();
        List<Order> orderList = Arrays.asList(order);

        List<OrderDetail> orderDetailList = Arrays.
                asList(OrderDetail.builder().orderId(1l).id(1l).bookId(3l).count(10).totalPrice(new BigDecimal(15l)).build(),
                        OrderDetail.builder().orderId(1l).id(2l).bookId(2l).count(20).totalPrice(new BigDecimal(5l)).build());
        List<OrderDetailDTO> orderDetailDTOList = Arrays.
                asList(OrderDetailDTO.builder().orderId(1l).id(1l).bookId(3l).count(10).totalPrice(new BigDecimal(15l)).build(),
                        OrderDetailDTO.builder().orderId(1l).id(2l).bookId(2l).count(20).totalPrice(new BigDecimal(5l)).build());

        when(orderDetailService.findOrderDetailListByOrderId(order.getId())).thenReturn(orderDetailList);
        when(orderDetailAdapter.prepareOrderDetailDtoList(orderDetailList)).thenReturn(orderDetailDTOList);

        List<OrderDTO> orderDTOList = orderService.getOrderDTOList(orderList);

        assertThat(orderDTOList).isNotEmpty();
        assertEquals(1, orderDTOList.size());
    }

    @Test
    void saveNewOrder_test() {
        List<Long> bookIdList = Arrays.asList(1l, 5l);
        List<BookDTO> bookDTOList = Arrays.asList(BookDTO.builder().id(1l).author("Henry").name("Elephants").unitPrice(new BigDecimal(10)).stockCount(100l).build(),
                BookDTO.builder().id(5l).author("Callum Chambers").name("Doctor Who").unitPrice(new BigDecimal(50)).stockCount(40l).build());
        OrderDTO orderDTO = OrderDTO.builder().customerId(2l).orderTime(DateTimeUtil.getLocalDateTime("2021-09-10 02:53:01")).orderStatus(OrderStatus.ACTIVE).totalOrderPrice(new BigDecimal(150)).build();
        List<OrderDetail> orderDetailList = Arrays.
                asList(OrderDetail.builder().orderId(1l).id(1l).bookId(1l).count(5).totalPrice(new BigDecimal(50)).build(),
                        OrderDetail.builder().orderId(1l).id(2l).bookId(5l).count(2).totalPrice(new BigDecimal(100)).build());
        List<OrderDetailDTO> orderDetailDTOList = Arrays.
                asList(OrderDetailDTO.builder().orderId(1l).id(1l).bookId(1l).count(5).totalPrice(new BigDecimal(50)).build(),
                        OrderDetailDTO.builder().orderId(1l).id(2l).bookId(5l).count(2).totalPrice(new BigDecimal(100)).build());
        orderDTO.setOrderDetailDTOList(orderDetailDTOList);

        when(bookClientService.getBookListByIds(bookIdList)).thenReturn(bookDTOList);
        when(sequenceService.getSequenceNumber(Order.SEQUENCE_NAME)).thenReturn(5);

        Order order = Order.builder().id(sequenceService.getSequenceNumber(Order.SEQUENCE_NAME)).customerId(2l).orderTime(DateTimeUtil.getLocalDateTime("2021-09-10 02:53:01")).orderStatus(OrderStatus.ACTIVE).totalOrderPrice(new BigDecimal(150)).build();

        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(orderDetailAdapter.prepareOrderDetailList(order, orderDetailDTOList)).thenReturn(orderDetailList);
        when(orderDetailService.saveAllOrderDetails(orderDetailList)).thenReturn(orderDetailList);
        when(orderDetailAdapter.prepareOrderDetailDtoList(orderDetailList)).thenReturn(orderDetailDTOList);
        when(orderAdapter.prepareOrderDto(order, orderDetailDTOList)).thenReturn(orderDTO);

        OrderDTO savedOrderDTO = orderService.saveNewOrder(orderDTO);
        assertThat(savedOrderDTO).isNotNull();
    }
}
