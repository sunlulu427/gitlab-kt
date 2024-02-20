import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("org.jetbrains.dokka")
}

group = "com.mato"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:3.12.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
}

tasks.withType<DokkaTask>().configureEach {
    doFirst {
        val title = """
            # Module gitlab

            > **Github Project**: [https://github.com/sunlulu427/gitlab-kt](https://github.com/sunlulu427/gitlab-kt)
        """.trimIndent()
        val resolved = configurations["runtimeClasspath"]
            .resolvedConfiguration
            .resolvedArtifacts
            .map { it.moduleVersion.id }
            .sortedBy { it.group + it.name }
        val maxGroupLength = maxOf(resolved.maxOf { it.group.length }, "group".length) + 2
        val maxNameLength = maxOf(resolved.maxOf { it.name.length }, "name".length) + 2
        val maxVersionLength = maxOf(resolved.maxOf { it.version.length }, "version".length) + 2
        fun String.padSpaces(maxLength: Int) = padStart(length + 1, ' ').padEnd(maxLength, ' ')
        val rows = mutableListOf(
            arrayOf(
                "group".padSpaces(maxGroupLength),
                "name".padSpaces(maxNameLength),
                "version".padSpaces(maxVersionLength)
            ).joinToString(separator = "|", prefix = "|", postfix = "|"),
            arrayOf(
                "-".repeat(maxGroupLength),
                "-".repeat(maxNameLength),
                "-".repeat(maxVersionLength)
            ).joinToString(separator = "|", prefix = "|", postfix = "|")
        )
        rows.addAll(
            resolved.map {
                arrayOf(
                    it.group.padSpaces(maxGroupLength),
                    it.name.padSpaces(maxNameLength),
                    it.version.padSpaces(maxVersionLength)
                ).joinToString(separator = "|", prefix = "|", postfix = "|")
            }
        )
        project.file("README.md").run {
            setWritable(true)
            writeText(title)
            appendText("\n")
            appendText(rows.joinToString("\n", prefix = "\n\n"))
            setReadOnly()
        }
    }
    dokkaSourceSets {
        named("main") {
            samples.from(getByName("test").sourceRoots)
            includes.from("README.md")
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}