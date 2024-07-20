dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))

    runtimeOnly(project(":infra"))
    runtimeOnly(project(":adapter:outbound:external"))
    runtimeOnly(project(":adapter:outbound:persistence"))

    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")

    testImplementation(project(":domain-test"))
}
