dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))
    implementation(project(":infra"))

    implementation("org.springframework:spring-context")

    testImplementation(project(":domain-test"))
    testImplementation("org.springframework.kafka:spring-kafka-test")
}
