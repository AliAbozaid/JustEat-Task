import dependencies.DebugDependencies
import dependencies.NavigationDependencies
import extensions.addTestsDependencies
import extensions.implementation

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    lintOptions {
        lintConfig = file(".lint/config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }

    buildTypes {
        getByName(BuildType.DEBUG) {
            isDebuggable = true
        }
        getByName(BuildType.RELEASE) {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile(AppConfig.proguardConsumerRules), AppConfig.proguardRules
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    api(AppDependencies.APPCOMPAT)
    api(AppDependencies.MATERIAL)
    implementation(NavigationDependencies.NAVIGATION_LIBRARIES)
    implementation(AppDependencies.CONSTRAINT_LAYOUT)
    debugImplementation(DebugDependencies.LEAKCANARY)
    addTestsDependencies()
}
