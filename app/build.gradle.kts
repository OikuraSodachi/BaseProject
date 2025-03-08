plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.todokanai.baseproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.todokanai.baseproject"
        minSdk = 30
        targetSdk = 31
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
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

}

dependencies {
    implementation(libs.androidx.recyclerview.selection)
    implementation(libs.firebase.common.ktx)
    implementation(libs.firebase.database.ktx)

    implementation (platform(libs.androidx.compose.bom))
    testImplementation (platform(libs.androidx.compose.bom))

    implementation (libs.androidx.ktx)
    implementation (libs.androidx.lifecycle.common)
    implementation (libs.androidx.activity.compose)

    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.junit)
    androidTestImplementation (libs.androidx.espresso.core)
    implementation (libs.androidx.lifecycle.runtime.compose.android)
    implementation (libs.androidx.constraintlayout)     // enable ConstraintLayout
    implementation (libs.material)
    implementation (libs.androidx.fragment.ktx)

    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)

    implementation (libs.androidx.legacy.support.v4)

    debugImplementation (libs.androidx.ui.tooling)
    implementation (libs.androidx.ui)
    implementation (libs.androidx.material3)

    implementation (libs.picasso)
    implementation (libs.androidx.hilt.navigation.compose)

    implementation (libs.coil.compose)
    implementation (libs.androidx.constraintlayout.compose)     // ConstraintLayout for compose
    implementation (libs.androidx.appcompat)

    implementation (libs.glide)
    annotationProcessor (libs.compiler)
    implementation (libs.hilt.android)
    kapt (libs.hilt.android.compiler)
    implementation (libs.androidx.datastore.preferences)       // enable DataStore
    implementation ("commons-net:commons-net:3.11.1")       /// ftp nas upload (?)

}