package org.blotit.discovery

import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DiscoveryIntegrationTest: StringSpec() {
    override fun extensions() = listOf(SpringExtension)

    init {
        "it should load the context" {
        }
    }
}