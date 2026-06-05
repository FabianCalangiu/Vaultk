plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp.plugin)
}

android {
    namespace = "com.unibo.android.data"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(project(":domain"))

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    //Moshi
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.codegen)


    //TODO ROOM 1. Aggiungi le dipendenze nel build.gradle.kts
    implementation("androidx.room:room-runtime:2.8.4")
    //TODO ROOM 1. Per generare il codice del DAO
    // oppure kapt("androidx.room:room-compiler:2.8.4" )
    ksp("androidx.room:room-compiler:2.8.4")
    //TODO ROOM 1. Se usi le Coroutines con Room
    implementation("androidx.room:room-ktx:2.8.4")

    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}