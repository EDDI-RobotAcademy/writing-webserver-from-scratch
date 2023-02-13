plugins {
    id("java")
}

group = "com.github.hyeyoom"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.eclipse.jetty:jetty-server:11.0.13")
    implementation("org.eclipse.jetty:jetty-servlet:11.0.13")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("org.mockito:mockito-core:5.1.1")
    implementation("com.samskivert:jmustache:1.15")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}