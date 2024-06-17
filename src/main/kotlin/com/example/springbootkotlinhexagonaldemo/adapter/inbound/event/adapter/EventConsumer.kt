package com.example.springbootkotlinhexagonaldemo.adapter.inbound.event.adapter

import org.springframework.stereotype.Component

@Component
class EventConsumer {

    fun consume(event: String) {
        // todo Consumer 구현 필요
        println(event)
    }
}