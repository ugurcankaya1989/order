package com.getir.order.util;

import com.getir.order.dto.BookDTO;
import com.getir.order.dto.OrderDTO;
import com.getir.order.dto.OrderDetailDTO;
import com.getir.order.exception.NoBookFoundByIdException;
import com.getir.order.exception.StockNotEnoughException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class OrderUtil {

    public static OrderDTO calculateTotalOrderPrice(OrderDTO orderDTO) {
        return orderDTO;
    }

    public static OrderDTO checkStockDetails(OrderDTO orderDTO, List<BookDTO> bookDTOList) {
        Map<Long, BookDTO> bookDTOMap = createMapForBookList(bookDTOList);
        AtomicReference<BigDecimal> totalOrderPrice = new AtomicReference<>(new BigDecimal(0));

        List<OrderDetailDTO> orderDetailDTOList = orderDTO.getOrderDetailDTOList();
        orderDetailDTOList.stream().forEach(orderDetail -> {
            BookDTO bookDTO = bookDTOMap.get(orderDetail.getBookId());
            if (Objects.nonNull(bookDTO)) {
                if (orderDetail.getCount() <= bookDTO.getStockCount()) {
                    BigDecimal totalPrice = calculateOrderDetailUnitPrice(bookDTO, orderDetail);
                    orderDetail.setTotalPrice(totalPrice);
                    totalOrderPrice.set(totalOrderPrice.get().add(totalPrice));;
                } else {
                    throw new StockNotEnoughException(orderDetail.getBookId());
                }
            } else {
                throw new NoBookFoundByIdException(orderDetail.getBookId());
            }
        });


        orderDTO.setTotalOrderPrice(totalOrderPrice.get());
        return orderDTO;
    }

    private static BigDecimal calculateOrderDetailUnitPrice(BookDTO bookDTO, OrderDetailDTO orderDetailDTO) {
        return bookDTO.getUnitPrice().multiply(new BigDecimal(orderDetailDTO.getCount()));
    }

    private static Map<Long, BookDTO> createMapForBookList(List<BookDTO> bookDTOList) {
        Map<Long, BookDTO> bookMap = new HashMap<>();
        bookDTOList.stream().forEach(bookDTO -> {
            bookMap.put(bookDTO.getId(), bookDTO);
        });
        return bookMap;
    }
}
