@Suppress("DSL_SCOPE_VIOLATION")
plugins {
   id("core-compose-module")
}


dependencies {
    //Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.bundles.compose.debug)

    api(libs.navigation)

    api(projects.core.ui)
    api(projects.core.di)

    implementation(projects.feature.home)
    implementation(projects.feature.imageViewer)
}