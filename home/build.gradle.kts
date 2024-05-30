plugins {
    id("hackertab.android.library")
    id("hackertab.android.library.compose")
    id("hackertab.android.feature")
}

android {
    namespace = "com.zrcoding.hackertab.home"
}

dependencies {
    implementation(project(":settings"))

    // Test
    testImplementation(libs.test.junit)
}