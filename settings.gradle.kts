rootProject.name = "sisterhood"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    ":sisterhood-application",
    ":sisterhood-application-android",
    ":sisterhood-base",
    ":sisterhood-hentai",
)

pluginManagement {
    repositories {
        google()
        mavenCentral()
    }
}
