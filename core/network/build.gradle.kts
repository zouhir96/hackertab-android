plugins {
    id("hackertab.android.library")
    id("hackertab.android.koin")
}

android {
    namespace = "com.zrcoding.hackertab.network"
}

dependencies {
    // Retrofit
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.converter)
    implementation(libs.squareup.okhttp3)
}