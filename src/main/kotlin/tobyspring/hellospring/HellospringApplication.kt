package tobyspring.hellospring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HellospringApplication

fun main(args: Array<String>) {
    runApplication<HellospringApplication>(*args)
}
