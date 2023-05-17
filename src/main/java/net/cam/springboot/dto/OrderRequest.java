package net.cam.springboot.dto;

import net.cam.springboot.entity.Order;
import net.cam.springboot.entity.Payment;

public record OrderRequest(Order order, Payment payment) {


}
