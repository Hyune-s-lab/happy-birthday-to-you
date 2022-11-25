import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "com.hyunec"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

val kotestVersion = "5.5.4"
val kotestExtVersion = "1.1.2"
val fixtureMonkeyVersion = "0.4.5"
val openapi3Version = "1.6.13"

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
