import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.serialization") version "1.9.21"
    id("org.jetbrains.dokka") version "1.9.10"
    id("maven-publish")
}

group = "com.mato"
archivesName = "gitlab"

repositories {
    mavenCentral()
}

dependencies {
    api(project(":gitlab"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

subprojects {
    apply(plugin = "maven-publish")
    apply(plugin = "java")
    System.getenv()["GITHUB_TOKEN"]?.let {
        configure<PublishingExtension> {
            repositories {
                maven {
                    name = "GitHubPackages"
                    url = uri("https://maven.pkg.github.com/sunlulu427/gitlab-kt")
                    credentials {
                        username = System.getenv("GITHUB_USERNAME")
                        password = it
                    }
                }
            }
            publications {
                register<MavenPublication>("jar") {
                    groupId = "com.mato"
                    artifactId = "gitlab"
                    version = "1.1"

                    afterEvaluate {
                        from(components["java"])
                    }
                }
            }
        }
    }
}