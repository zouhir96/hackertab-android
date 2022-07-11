val coreKtxVersion by extra("1.7.0")

buildscript {
    val kotlinVersion = "1.7.0"
    val hiltVersion = "2.42"

    dependencies {
        classpath("com.android.tools.build:gradle:4.1.2")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")

        // kotlin
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-android-extensions:$kotlinVersion")
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.1.3" apply false
    id("com.android.library") version "7.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.5.30" apply false
    id("com.google.dagger.hilt.android") version "2.42" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}