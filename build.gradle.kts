plugins {
    java
}

subprojects {

    apply {
        plugin("java")
    }

    group = "org.example"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    tasks.withType<Jar> {
        manifest {
            attributes["Main-Class"] = "org.example.Main"
        }
    }

}

