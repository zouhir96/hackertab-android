plugins {
    id("hackertab.android.library")
    id("hackertab.android.hilt")
    alias(libs.plugins.google.devtools.ksp)
}

android {
    namespace = "com.zrcoding.shared"
}

dependencies {

    // Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)

    // Retrofit
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.converter)
    implementation(libs.squareup.okhttp3)
}