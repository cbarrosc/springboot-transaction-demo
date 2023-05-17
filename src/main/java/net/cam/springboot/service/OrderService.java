package net.cam.springboot.service;

import net.cam.springboot.dto.OrderRequest;
import net.cam.springboot.dto.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest orderRequest);

}
