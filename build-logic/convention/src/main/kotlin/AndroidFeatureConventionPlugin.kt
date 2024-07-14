import com.android.build.gradle.LibraryExtension
import com.zrcoding.convention.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("hackertab.android.library")
                apply("hackertab.android.hilt")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner =
                        "com.google.samples.apps.nowinandroid.core.testing.NiaTestRunner"
                }
            }

            dependencies {
                add("implementation", project(":core:design"))
                add("implementation", project(":core:database"))
                add("implementation", project(":core:network"))

                add("testImplementation", kotlin("test"))
                add("androidTestImplementation", kotlin("test"))

                add("implementation", versionCatalog().findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", versionCatalog().findLibrary("androidx.lifecycle.runtime.ktx").get())
                add("implementation", versionCatalog().findLibrary("androidx.lifecycle.runtime.compose").get())
            }
        }
    }
}