plugins {
    kotlin("jvm") version "2.1.0"
    application
}

group = "cn.enaium"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}

application {
    mainClass = "MainKt"
    applicationDefaultJvmArgs = listOf("-Djava.library.path=SDL/build/Release", "--enable-native-access=ALL-UNNAMED")
}