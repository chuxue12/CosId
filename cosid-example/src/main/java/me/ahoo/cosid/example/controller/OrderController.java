/*
 * Copyright [2021-2021] [ahoo wang <ahoowang@qq.com> (https://github.com/Ahoo-Wang)].
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.ahoo.cosid.example.controller;

import com.google.common.base.Splitter;
import me.ahoo.cosid.example.entity.Order;
import me.ahoo.cosid.example.entity.OrderItem;
import me.ahoo.cosid.example.repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ahoo wang
 */
@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    @PostMapping("/{userId}")
    public long create(@PathVariable long userId) {
        Order order = new Order();
        order.setUserId(userId);
        orderRepository.insert(order);
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setOrderId(order.getOrderId());
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setOrderId(order.getOrderId());
        orderRepository.insertItem(orderItem1);
        orderRepository.insertItem(orderItem2);
        return order.getOrderId();
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable long id) {
        return orderRepository.getById(id);
    }

    @GetMapping("/list/{ids}")
    public List<Order> get(@PathVariable String ids) {
        List<Long> idList = Splitter.on(",").omitEmptyStrings().trimResults().splitToStream(ids).map(Long::parseLong).collect(Collectors.toList());
        return orderRepository.getByIds(idList);
    }

}
