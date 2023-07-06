plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.10.0")
    implementation("junit:junit:4.13.1")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    implementation("com.github.vladislavsevruk:recursive-assertion-assertj:1.0.1")
    implementation("com.github.vladislavsevruk:recursive-assertion-testng:1.0.1")
    implementation("cn.minsin:mutils-core:0.3.7")
    testImplementation("junit:junit:4.13.1")
    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("org.testng:testng:7.8.0")
    implementation("org.testng:testng:7.8.0")
}

tasks.test {
    useJUnitPlatform()
}