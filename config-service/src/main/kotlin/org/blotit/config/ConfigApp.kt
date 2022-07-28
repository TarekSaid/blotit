package org.blotit.config

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class ConfigApp

fun main(args: Array<String>) {
    runApplication<ConfigApp>(*args)
}