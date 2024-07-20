dependencies {
    implementation(project(":domain"))

    compileOnly("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.kafka:spring-kafka")
}
