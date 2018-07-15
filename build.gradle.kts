import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URL

plugins {
    java
    kotlin("jvm") version "1.2.51"
    `maven-publish`
    maven
}

group = "com.rnett.ligraph.eve"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven ("https://dl.bintray.com/kotlin/exposed")
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

val sourcesJar by tasks.creating(Jar::class) {
    classifier = "sources"
    from(java.sourceSets["main"].allSource)
}

artifacts.add("archives", sourcesJar)

publishing {
    publications {
        create("default", MavenPublication::class.java) {
            from(components["java"])
            artifact(sourcesJar)
        }
        create("mavenJava", MavenPublication::class.java) {
            from(components["java"])
            artifact(sourcesJar)
        }
    }
    repositories {
        maven {
            url = uri("$buildDir/repository")
        }
    }
}