plugins {
    // Applies the common conventions plugin for the URL shortener project.
    id("urlshortener-common-conventions")
    // Applies the Kotlin Spring plugin using an alias from the version catalog.
    alias(libs.plugins.kotlin.spring)
    // Applies the Spring Dependency Management plugin using an alias from the version catalog.
    alias(libs.plugins.spring.dependency.management)

    id("org.springframework.boot") version "3.1.0"
    
}

dependencies {
    implementation(project(":core"))
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.google.code.gson:gson:2.8.8")
    implementation("org.springframework.boot:spring-boot-starter") {
        exclude(group = "commons-logging", module = "commons-logging")
    }
}

configurations.matching { it.name == "detekt" }.all {
    resolutionStrategy.eachDependency {
        // Ensures that all dependencies from the org.jetbrains.kotlin group use version 1.9.23.
        if (requested.group == "org.jetbrains.kotlin") {
            useVersion("1.9.23")
        }
    }
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}