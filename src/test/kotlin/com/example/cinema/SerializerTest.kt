package com.example.cinema

import arrow.syntax.collections.prependTo
import io.kotest.core.spec.style.StringSpec

//@SpringBootTest
class SerializerTest() : StringSpec() {

    init {
        "should serialize" {
//            val strValue = objectMapper.writeValueAsString(
//                    Screening(13, 15, LocalDate.now(), LocalTime.now(), 123)
//            )
//            println(strValue)
        }

        "mono error test" {
            val listA = listOf<Int>(1, 2, 3)

            println(5.prependTo(listA))
        }
    }

}