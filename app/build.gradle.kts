plugins {
    id("com.android.application")
}

android {
    namespace = "org.ifup.simplevolume"
    compileSdk = 36

    defaultConfig {
        applicationId = "org.ifup.simplevolume"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"
    }

    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }
}
