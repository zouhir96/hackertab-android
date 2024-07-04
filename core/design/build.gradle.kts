plugins {
    id("hackertab.android.library")
    id("hackertab.android.library.compose")
}

android {
    namespace = "com.zrcoding.hackertab.design"
}

dependencies {
    // UI
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.material)
    api(libs.io.coil.compose)
    api(libs.io.coil.gif)

    // Tools
    debugApi(libs.androidx.compose.ui.tooling)
}