plugins {
    alias(libs.plugins.android.application)
//    ObjectBox plugin
    id("io.objectbox")
}
val latestFastAdapterRelease="5.7.0"

android {
    namespace = "com.gshoaib998.moneytracker"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.gshoaib998.moneytracker"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures{
        dataBinding=true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
//object box dependency
    implementation(libs.objectbox.android)
//fast adapter dependencies
    implementation(libs.fastadapter)
    implementation(libs.fastadapter.extensions.expandable)
    implementation (libs.fastadapter.extensions.binding) // view binding helpers
    implementation (libs.fastadapter.extensions.diff) // diff util helpers
    implementation (libs.fastadapter.extensions.drag) // drag support
    implementation (libs.fastadapter.extensions.paged )// paging support
    implementation (libs.fastadapter.extensions.scroll )// scroll helpers
    implementation (libs.fastadapter.extensions.swipe) // swipe support
    implementation (libs.fastadapter.extensions.ui )// pre-defined ui components
    implementation (libs.fastadapter.extensions.utils)

}