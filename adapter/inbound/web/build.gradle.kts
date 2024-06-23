dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))
    implementation(project(":adapter:outbound:external"))
    implementation(project(":adapter:outbound:persistence"))

    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
}
