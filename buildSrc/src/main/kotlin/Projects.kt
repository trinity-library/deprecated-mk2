package sisterhood

import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

val androidCompileSdk: Int
    get() = 34

val androidMinSdk: Int
    get() = 30

val androidNamespace: String
    get() = "trinity.sisterhood"

fun Project.androidWithDefault(configure: LibraryExtension.() -> Unit = {}) {
    androidExtension {
        compileSdk = androidCompileSdk
        namespace = androidNamespace

        buildFeatures {
            compose = true
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        defaultConfig {
            minSdk = androidMinSdk
        }
        testOptions {
            unitTests.all {
                it.useJUnitPlatform()
            }
        }
        configure()
    }
}

private fun Project.androidExtension(configure: LibraryExtension.() -> Unit) {
    extensions.configure("android", configure)
}
