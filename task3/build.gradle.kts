plugins {
    id ("com.github.johnrengelman.shadow") version "7.0.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")
}