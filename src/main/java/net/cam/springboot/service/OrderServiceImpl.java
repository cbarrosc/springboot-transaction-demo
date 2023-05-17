package net.cam.springboot.service;


import lombok.RequiredArgsConstructor;
import net.cam.springboot.dto.OrderRequest;
import net.cam.springboot.dto.OrderResponse;
import net.cam.springboot.entity.Order;
import net.cam.springboot.entity.Payment;
import net.cam.springboot.exception.PaymentException;
import net.cam.springboot.repository.OrderRepository;
import net.cam.springboot.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional(rollbackFor = PaymentException.class)
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = orderRequest.order();
        order.setStatus("INPROGRESS");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = orderRequest.payment();

        if (!payment.getType().equals("DEBIT")) {
            throw new PaymentException("Payment card type not supported");
        }
        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        return OrderResponse.builder()
                .orderTrackingNumber(order.getOrderTrackingNumber())
                .status(order.getStatus())
                .message("Order placed successfully")
                .build();
    }
}
