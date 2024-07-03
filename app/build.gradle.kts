import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("hackertab.android.application")
    id("hackertab.android.application.compose")
    id("hackertab.android.hilt")
}

android {
    namespace = "com.zrcoding.hackertab"

    defaultConfig {
        applicationId = "com.zrcoding.hackertab"
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
    implementation(project(":design"))
    implementation(project(":shared"))
    implementation(project(":feature:home"))
    implementation(project(":feature:settings"))

    // Activity
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.activity)

    implementation(libs.androidx.compose.materialWindow)
    implementation(libs.androidx.navigation)
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