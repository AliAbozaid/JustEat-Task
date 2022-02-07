object AppDependencies {
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${BuildDependenciesVersions.CONSTRAINT_LAYOUT}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${BuildDependenciesVersions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${BuildDependenciesVersions.MATERIAL}"
    //  TODO   const val KOTLIN_STD_LIB = "org.jetbrains.kotlin:kotlin-stdlib:${BuildDependenciesVersions.KOTLIN}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${BuildDependenciesVersions.KOTLIN}"

    // coroutinesDependencies
    private const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDependenciesVersions.COROUTINES}"
    private const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildDependenciesVersions.COROUTINES}"

    val COROUTINES_LIBRARIES = arrayListOf<String>().apply {
        add(COROUTINES_CORE)
        add(COROUTINES_ANDROID)
    }

    const val MOCKK = "io.mockk:mockk:${BuildDependenciesVersions.MOCKK}"
    const val JUNIT = "junit:junit:${BuildDependenciesVersions.JUNIT}"
    const val COROUTINES_CORE_TEST =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDependenciesVersions.COROUTINES}"
    const val COROUTINES_TEST =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${BuildDependenciesVersions.COROUTINES_TEST}"
    const val JUPITER = "org.junit.jupiter:junit-jupiter-api:${BuildDependenciesVersions.JUPITER}"

    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${BuildDependenciesVersions.ANDROIDX_JUNIT}"
    const val ESPRESSO =
        "androidx.test.espresso:espresso-core:${BuildDependenciesVersions.ESPRESSO}"

    const val ASSERTJ = "org.assertj:assertj-core:${BuildDependenciesVersions.ASSERTJ}"

    // common
    const val TIMBER = "com.jakewharton.timber:timber:${BuildDependenciesVersions.TIMBER}"

    const val KTLINT = "com.pinterest:ktlint:${BuildDependenciesVersions.KTLINT}"

    // detekt
    const val DETEKT =
        "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${AppConfig.detektVersion}"

    // coil
    const val COIL = "io.coil-kt:coil:${BuildDependenciesVersions.COIL}"


}
