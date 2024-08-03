plugins {
    id("hackertab.android.library")
    id("hackertab.android.library.compose")
    id("hackertab.android.feature")
    alias(libs.plugins.jetbrains.kotlinx.serialization)
}

android {
    namespace = "com.zrcoding.hackertab.settings"


    defaultConfig {
        buildConfigField("String", "VERSION_NAME", "\"${libs.versions.versionName.get()}\"")
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    // Datastore
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore.preferences.core)

    implementation(libs.kotlinx.serialization.json)
}