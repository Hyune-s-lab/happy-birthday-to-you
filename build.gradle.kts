import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    id("org.jmailen.kotlinter") version "3.10.0"
}

group = "com.hyunec"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

val kotestVersion: String by project
val kotestExtVersion: String by project
val fixtureMonkeyVersion: String by project
val openapi3Version: String by project

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.apache.commons:commons-csv:1.9.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // m1 에서 실행시 오류 해결
    implementation("io.netty:netty-resolver-dns-native-macos:4.1.85.Final:osx-aarch_64")

    // openapi3
    implementation("org.springdoc:springdoc-openapi-ui:$openapi3Version")
    implementation("org.springdoc:springdoc-openapi-webflux-ui:$openapi3Version")
    implementation("org.springdoc:springdoc-openapi-kotlin:$openapi3Version")

    // kotest
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:$kotestExtVersion")

    // fixture-monkey
    implementation("com.navercorp.fixturemonkey:fixture-monkey-jackson:$fixtureMonkeyVersion")
    implementation("com.navercorp.fixturemonkey:fixture-monkey-kotlin:$fixtureMonkeyVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
