plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:${Versions.material}")
    implementation("androidx.appcompat:appcompat:${Versions.appCompat}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}")
}

android {
    compileSdkVersion(Versions.targetSdkVersion)

    defaultConfig {
        applicationId = "com.sfkit.androidApp"
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = 1
        versionName = "0.1"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}