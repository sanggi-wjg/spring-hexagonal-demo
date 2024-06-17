package com.example.springbootkotlinhexagonaldemo.adapter.outbound.external.adapter

import org.springframework.stereotype.Component

@Component
class EventProducer {

    fun publish(event: String) {
        // todo Producer 구현 필요
        println(event)
    }
}