dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))
    implementation(project(":infra"))

    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
}
