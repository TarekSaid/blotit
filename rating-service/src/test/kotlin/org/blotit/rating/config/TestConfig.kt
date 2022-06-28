package org.blotit.rating.config

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.IsolationMode

class TestConfig: AbstractProjectConfig() {
    override val isolationMode = IsolationMode.InstancePerTest
}