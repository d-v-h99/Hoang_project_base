buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
        maven(url = "https://plugins.gradle.org/m2/")
        flatDir {
            dirs("libs")
        }
    }

    dependencies {
        classpath("io.realm:realm-gradle-plugin:10.19.0")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
}