plugins {
    id("hackertab.android.library")
    id("hackertab.android.koin")
    alias(libs.plugins.jetbrains.kotlinx.serialization)
}

android {
    namespace = "com.zrcoding.hackertab.network"
}

dependencies {
    // Ktor
    implementation(libs.ktor.core)
    implementation(libs.ktor.okhttp)
    implementation(libs.ktor.contentNegotiation)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.loggingInterceptor)
}