import dependencies.DebugDependencies
import dependencies.KoinDependencies
import dependencies.NavigationDependencies
import extensions.addTestsDependencies
import extensions.implementation


val buildVersionCode = Integer.parseInt(System.getenv("VERSION_CODE") ?: "1")
val buildVersionName = System.getenv("VERSION_NAME") ?: "0.0.1"

plugins {
	id("com.android.application")
	kotlin("android")
	id("kotlin-android")
	kotlin("kapt")
	id("kotlin-parcelize")
}

android {
	compileSdk = AppConfig.compileSdk

	defaultConfig {
		applicationId = "io.aliabozid.justeat"
		minSdk = AppConfig.minSdkVersion
		targetSdk = AppConfig.targetSdkVersion
		versionCode = buildVersionCode
		versionName = buildVersionName
		testInstrumentationRunner = AppConfig.androidTestInstrumentation

		// Add new languages here
		resConfigs("en")
	}

	buildFeatures {
		viewBinding = true
	}

	signingConfigs {
		getByName("debug") {
			storeFile = file("debug.keystore")
			keyAlias = "androiddebugkey"
			keyPassword = "android"
			storePassword = "android"
		}

		create("release") {

		}
	}

	buildTypes {
		getByName("debug") {
			isDebuggable = true
			applicationIdSuffix = ".debug"
//            the<CrashlyticsExtension>().mappingFileUploadEnabled = false
		}
		getByName("release") {
			isMinifyEnabled = true
			signingConfig = signingConfigs.getByName("release")
//            the<CrashlyticsExtension>().mappingFileUploadEnabled = true
			proguardFiles(
				getDefaultProguardFile(AppConfig.proguardConsumerRules),
				AppConfig.proguardRules
			)
		}
	}

	flavorDimensions(AppConfig.dimension)

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

	lintOptions {
		lintConfig = file(".lint/config.xml")
		isCheckAllWarnings = true
		isWarningsAsErrors = true
	}

	testOptions {
		unitTests.isIncludeAndroidResources = true
		unitTests.isReturnDefaultValues = true
	}

}

dependencies {
	implementation(project(":feature:navigation"))
	implementation(project(":feature:common"))
	implementation(project(":feature:restaurants"))
	implementation(AppDependencies.KOTLIN)

	// Presentation
	implementation(NavigationDependencies.NAVIGATION_LIBRARIES)
	implementation(AppDependencies.CONSTRAINT_LAYOUT)

	// Koin
	implementation(KoinDependencies.KOIN_LIBRARIES)

	// Coroutines
	implementation(AppDependencies.COROUTINES_LIBRARIES)

	implementation(AppDependencies.TIMBER)

	debugImplementation(DebugDependencies.LEAKCANARY)

	addTestsDependencies()
}
