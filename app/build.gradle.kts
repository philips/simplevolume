plugins {
    id("com.android.application")
}

// Release signing is optional locally; CI provides these via environment variables.
val keystoreFile = System.getenv("KEYSTORE_FILE").orEmpty()
val keystorePassword = System.getenv("KEYSTORE_PASSWORD").orEmpty()
val keyAliasEnv = System.getenv("KEY_ALIAS").orEmpty()
val keyPasswordEnv = System.getenv("KEY_PASSWORD").orEmpty()
val hasReleaseSigning = listOf(keystoreFile, keystorePassword, keyAliasEnv, keyPasswordEnv)
    .all { it.isNotBlank() }

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

    signingConfigs {
        if (hasReleaseSigning) {
            create("release") {
                storeFile = file(keystoreFile)
                storePassword = keystorePassword
                keyAlias = keyAliasEnv
                keyPassword = keyPasswordEnv
            }
        }
    }

    buildTypes {
        release {
            if (hasReleaseSigning) signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }
}

// Prints the version for CI, e.g. `./gradlew -q printVersion`.
tasks.register("printVersion") {
    val versionName = android.defaultConfig.versionName
    doLast { println(versionName) }
}
