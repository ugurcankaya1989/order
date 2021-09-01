package com.getir.order.init;

import com.getir.order.constant.OrderStatus;
import com.getir.order.model.Order;
import com.getir.order.model.OrderDetail;
import com.getir.order.repository.OrderDetailRepository;
import com.getir.order.repository.OrderRepository;
import com.getir.order.service.SequenceService;
import com.getir.order.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class LoadInitialData {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final SequenceService sequenceService;

    public void loadOrderList() {

        int orderId1 = sequenceService.getSequenceNumber(Order.SEQUENCE_NAME);
        Order order1 = Order.builder().id(orderId1).orderTime(DateTimeUtil.getLocalDateTime("2021-05-08 12:30:01")).orderStatus(OrderStatus.COMPLETED).totalOrderPrice(new BigDecimal(1110)).customerId(1).build();
        orderRepository.save(order1);
        OrderDetail orderDetail1 = OrderDetail.builder().id(sequenceService.getSequenceNumber(OrderDetail.SEQUENCE_NAME)).orderId(orderId1).bookId(1).count(3).totalPrice(new BigDecimal(360)).build();
        OrderDetail orderDetail2 = OrderDetail.builder().id(sequenceService.getSequenceNumber(OrderDetail.SEQUENCE_NAME)).orderId(orderId1).bookId(2).count(5).totalPrice(new BigDecimal(750)).build();
        orderDetailRepository.save(orderDetail1);
        orderDetailRepository.save(orderDetail2);

        int orderId2 = sequenceService.getSequenceNumber(Order.SEQUENCE_NAME);
        Order order2 = Order.builder().id(orderId2).orderTime(DateTimeUtil.getLocalDateTime("2021-06-01 12:30:01")).orderStatus(OrderStatus.COMPLETED).totalOrderPrice(new BigDecimal(1150)).customerId(1).build();
        orderRepository.save(order2);
        OrderDetail orderDetail3 = OrderDetail.builder().id(sequenceService.getSequenceNumber(OrderDetail.SEQUENCE_NAME)).orderId(orderId2).bookId(2).count(1).totalPrice(new BigDecimal(150)).build();
        OrderDetail orderDetail4 = OrderDetail.builder().id(sequenceService.getSequenceNumber(OrderDetail.SEQUENCE_NAME)).orderId(orderId2).bookId(3).count(2).totalPrice(new BigDecimal(1000)).build();
        orderDetailRepository.save(orderDetail3);
        orderDetailRepository.save(orderDetail4);

        int orderId3 = sequenceService.getSequenceNumber(Order.SEQUENCE_NAME);
        Order order3 = Order.builder().id(orderId3).orderTime(DateTimeUtil.getLocalDateTime("2021-08-08 12:30:01")).orderStatus(OrderStatus.ACTIVE).totalOrderPrice(new BigDecimal(3640)).customerId(1).build();
        orderRepository.save(order3);
        OrderDetail orderDetail5 = OrderDetail.builder().id(sequenceService.getSequenceNumber(OrderDetail.SEQUENCE_NAME)).orderId(orderId3).bookId(1).count(2).totalPrice(new BigDecimal(240)).build();
        OrderDetail orderDetail6 = OrderDetail.builder().id(sequenceService.getSequenceNumber(OrderDetail.SEQUENCE_NAME)).orderId(orderId3).bookId(3).count(4).totalPrice(new BigDecimal(2000)).build();
        OrderDetail orderDetail7 = OrderDetail.builder().id(sequenceService.getSequenceNumber(OrderDetail.SEQUENCE_NAME)).orderId(orderId3).bookId(5).count(7).totalPrice(new BigDecimal(1400)).build();
        orderDetailRepository.save(orderDetail5);
        orderDetailRepository.save(orderDetail6);
        orderDetailRepository.save(orderDetail7);

        int orderId4 = sequenceService.getSequenceNumber(Order.SEQUENCE_NAME);
        Order order4 = Order.builder().id(orderId4).orderTime(DateTimeUtil.getLocalDateTime("2021-08-08 12:30:01")).orderStatus(OrderStatus.ACTIVE).totalOrderPrice(new BigDecimal(360)).customerId(2).build();
        orderRepository.save(order4);
        OrderDetail orderDetail8 = OrderDetail.builder().id(sequenceService.getSequenceNumber(OrderDetail.SEQUENCE_NAME)).orderId(orderId4).bookId(1).count(3).totalPrice(new BigDecimal(360)).build();
        orderDetailRepository.save(orderDetail8);

        int orderId5 = sequenceService.getSequenceNumber(Order.SEQUENCE_NAME);
        Order order5 = Order.builder().id(orderId5).orderTime(DateTimeUtil.getLocalDateTime("2021-03-08 12:30:01")).orderStatus(OrderStatus.ACTIVE).totalOrderPrice(new BigDecimal(300)).customerId(3).build();
        orderRepository.save(order5);
        OrderDetail orderDetail9 = OrderDetail.builder().id(sequenceService.getSequenceNumber(OrderDetail.SEQUENCE_NAME)).orderId(orderId5).bookId(4).count(3).totalPrice(new BigDecimal(300)).build();
        orderDetailRepository.save(orderDetail9);


        int orderId6 = sequenceService.getSequenceNumber(Order.SEQUENCE_NAME);
        Order order6 = Order.builder().id(orderId6).orderTime(DateTimeUtil.getLocalDateTime("2021-03-08 12:30:01")).orderStatus(OrderStatus.ACTIVE).totalOrderPrice(new BigDecimal(2040)).customerId(3).build();
        orderRepository.save(order6);
        OrderDetail orderDetail10 = OrderDetail.builder().id(sequenceService.getSequenceNumber(OrderDetail.SEQUENCE_NAME)).orderId(orderId6).bookId(4).count(3).totalPrice(new BigDecimal(300)).build();
        orderDetailRepository.save(orderDetail10);
        OrderDetail orderDetail11 = OrderDetail.builder().id(sequenceService.getSequenceNumber(OrderDetail.SEQUENCE_NAME)).orderId(orderId6).bookId(1).count(2).totalPrice(new BigDecimal(240)).build();
        orderDetailRepository.save(orderDetail11);
        OrderDetail orderDetail12 = OrderDetail.builder().id(sequenceService.getSequenceNumber(OrderDetail.SEQUENCE_NAME)).orderId(orderId6).bookId(5).count(3).totalPrice(new BigDecimal(1500)).build();
        orderDetailRepository.save(orderDetail12);
    }

}
