plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    val room_version = "2.4.2"
    val retrofit_version = "2.9.0"
    val hilt_version = "2.42"

    // kotlin
    implementation("androidx.core:core-ktx:${rootProject.extra["coreKtxVersion"]}")

    // Coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.3")

    // room
    api("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    api("androidx.room:room-ktx:$room_version")

    // Retrofit
    api("com.squareup.retrofit2:retrofit:$retrofit_version")
    api("com.squareup.retrofit2:converter-gson:$retrofit_version")
    api("com.squareup.okhttp3:logging-interceptor:4.8.0")

    // di: hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")

    // Tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}