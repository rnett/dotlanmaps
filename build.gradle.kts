import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URL

plugins {
    java
    kotlin("jvm") version "1.2.51"
}

group = "com.rnett.ligraph.eve"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven ("https://dl.bintray.com/kotlin/exposed")
    maven("https://jitpack.io")
    maven("https://dl.bintray.com/kotlin/ktor")
    jcenter()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("com.github.salomonbrys.kotson:kotson:2.5.0")

}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}