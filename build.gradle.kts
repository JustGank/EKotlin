import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}
group = "me.xujinlong"
version = "1.0-SNAPSHOT"





repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")

}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}