package commons

import AppConfig
import AppDependencies
import dependencies.KoinDependencies
import extensions.addTestsDependencies

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("kotlin-allopen")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
    }

    flavorDimensions(AppConfig.dimension)
    productFlavors {
        create("development") {
        }
        create("staging") {
        }
        create("production") {
        }
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    // Kotlin
    implementation(AppDependencies.KOTLIN)
    // Timber
    implementation(AppDependencies.TIMBER)
    // Koin
    implementation(KoinDependencies.KOIN_LIBRARIES)
    // Coroutines
    implementation(AppDependencies.COROUTINES_LIBRARIES)
    // Test
    addTestsDependencies()
}
