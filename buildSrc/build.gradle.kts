plugins {
    id("groovy") // 引入 Groovy 插件
    kotlin("jvm") version "1.9.24"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.codehaus.groovy:groovy-all:3.0.9") // 确保依赖也在主项目中
}