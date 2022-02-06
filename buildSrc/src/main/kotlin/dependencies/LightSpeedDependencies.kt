package dependencies

import BuildDependenciesVersions

object LightSpeedDependencies {
    private const val LIGHT_SPEED_CORE = "cz.cleevio.gitlab.lightspeed.lightbase:core:${BuildDependenciesVersions.LIGHT_SPEED}"
    private const val LIGHT_SPEED_PIN = "cz.cleevio.gitlab.lightspeed.lightbase:pin:${BuildDependenciesVersions.LIGHT_SPEED}"
    private const val LIGHT_SPEED_COUNTRY_PICKER = "cz.cleevio.gitlab.lightspeed.lightbase:countryPicker:${BuildDependenciesVersions.LIGHT_SPEED}"
    private const val LIGHT_SPEED_CAMERA = "cz.cleevio.gitlab.lightspeed.lightbase:camera:${BuildDependenciesVersions.LIGHT_SPEED}"
    private const val LIGHT_SPEED_ON_BOARDING = "cz.cleevio.gitlab.lightspeed.lightbase:onboarding:${BuildDependenciesVersions.LIGHT_SPEED}"

    val LIGHT_SPEED_DEPENDENCIES = arrayListOf<String>().apply {
        add(LIGHT_SPEED_CORE)
        add(LIGHT_SPEED_PIN)
        add(LIGHT_SPEED_COUNTRY_PICKER)
        add(LIGHT_SPEED_CAMERA)
        add(LIGHT_SPEED_ON_BOARDING)
    }
}
