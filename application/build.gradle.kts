dependencies {
    implementation(project(":domain"))

    implementation("org.springframework:spring-tx")

    testImplementation(project(":domain-test"))
}
