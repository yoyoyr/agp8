plugins {
    kotlin("jvm") version "1.9.24"
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("org.ow2.asm:asm:9.6")
    implementation("org.ow2.asm:asm-commons:9.6")
    implementation(gradleApi())
    implementation("com.android.tools.build:gradle-api:8.7.3")
}