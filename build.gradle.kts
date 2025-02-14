// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.googleservices) version libs.versions.googleservices apply false
    alias(libs.plugins.appdistribution) version libs.versions.appdistribution apply false
    alias(libs.plugins.detekt) version libs.versions.detekt apply false
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
    reports {
        html.required.set(false)
        xml.required.set(false)
        txt.required.set(false)
    }
    autoCorrect = true
    config.setFrom("$projectDir/config/detekt.yml")
    baseline = file("$projectDir/config/baseline.xml")
}