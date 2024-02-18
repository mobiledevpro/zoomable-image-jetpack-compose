pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "App-Template-Compose"

include(":app")
include(":core:ui")
include(":core:navigation")
include(":core:coroutines")

include(":feature:home")
include(":core:di")
include(":core:util")
include(":feature:image_viewer")
