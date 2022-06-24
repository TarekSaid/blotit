import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    groovy
}

group = "org.blotit"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.codehaus.groovy:groovy-all:3.0.11")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation(platform("org.spockframework:spock-bom:2.1-groovy-3.0"))
    testImplementation("org.spockframework:spock-core")
    testImplementation("org.spockframework:spock-spring")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
    testLogging {
        events(PASSED, SKIPPED, FAILED)
    }
}