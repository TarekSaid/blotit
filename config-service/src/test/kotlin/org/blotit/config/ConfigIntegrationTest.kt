package org.blotit.config

import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ConfigIntegrationTest : StringSpec() {
    override fun extensions() = listOf(SpringExtension)

    init {
        "it should load the context" {
        }
    }
}