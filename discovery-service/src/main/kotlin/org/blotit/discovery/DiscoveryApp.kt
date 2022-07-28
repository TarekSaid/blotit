package org.blotit.discovery

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class DiscoveryApp

fun main(args: Array<String>) {
    runApplication<DiscoveryApp>(*args)
}