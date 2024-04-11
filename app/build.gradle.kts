import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.zrcoding.hackertab"

    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.zrcoding.hackertab"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 3
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        libs.versions.jvmTarget.get()
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.depComposeCompiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":design"))
    implementation(project(":shared"))

    // Core
    implementation(libs.androidx.core.ktx)
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    // Activity
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.activity)

    // Navigation
    implementation(libs.androidx.navigation)
    implementation(libs.dagger.hilt.navigation)

    // LifeCycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)

    // di: hilt
    implementation(libs.google.dagger.hilt.android)
    kapt(libs.google.dagger.hilt.android.compiler)

    // Test
    testImplementation(libs.test.junit)
}

tasks.withType<Test> {
    testLogging {
        // set options for log level LIFECYCLE
        events = setOf(
            TestLogEvent.FAILED,
            TestLogEvent.PASSED,
            TestLogEvent.SKIPPED,
            TestLogEvent.STANDARD_OUT
        )
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}