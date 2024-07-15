import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("hackertab.android.application")
    id("hackertab.android.application.compose")
    id("hackertab.android.koin")
}

android {
    namespace = "com.zrcoding.hackertab"

    defaultConfig {
        applicationId = "com.zrcoding.hackertab"
        // The CI will take care of incrementing this using the build number.
        versionCode = 3
        versionName = libs.versions.versionName.get()
    }

    buildFeatures {
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":feature:home"))
    implementation(project(":feature:settings"))
    implementation(project(":core:network"))

    // Activity
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.activity)

    implementation(libs.androidx.compose.materialWindow)
    implementation(libs.androidx.navigation)

    implementation(platform(libs.com.google.firebase.bom))
    implementation(libs.com.google.firebase.analytics)
    implementation(libs.com.google.firebase.crashlytics)
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