rootProject.name = "sisterhood"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    "sisterhood-application-android",
    "sisterhood-compose",
    "sisterhood-hentai",
)

pluginManagement {
    repositories {
        google()
        mavenCentral()
    }
}
