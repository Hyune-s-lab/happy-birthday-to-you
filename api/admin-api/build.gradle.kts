plugins {

}

dependencies {
    val openapi3Version: String by project

    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("org.apache.commons:commons-csv:1.9.0")

    // openapi3
    implementation("org.springdoc:springdoc-openapi-ui:$openapi3Version")
    implementation("org.springdoc:springdoc-openapi-webflux-ui:$openapi3Version")
    implementation("org.springdoc:springdoc-openapi-kotlin:$openapi3Version")
}

tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}
