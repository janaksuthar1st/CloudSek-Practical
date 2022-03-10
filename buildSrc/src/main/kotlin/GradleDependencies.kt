const val KOTLIN_VERSION = "1.5.0"
const val KOIN_VERSION = "2.2.2"

/**
 * Project level
 */
object BuildPlugins {

    object BuildPluginVersions{
        val GRADLE_TOOL = "4.2.1"
    }


    val classpath_kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${KOTLIN_VERSION}"
    val classpath_gradle_tool = "com.android.tools.build:gradle:${BuildPluginVersions.GRADLE_TOOL}"
    val koin = "org.koin:koin-gradle-plugin:$KOIN_VERSION"
}

/**
 * App level
 */
object GradleDependencies {

    object Versions {
        const val APPCOMPAT = "1.3.0"
        const val ROOM_DATABASE = "2.2.6"
        const val KTX_CORE = "1.5.0"
        const val CONSTRAINT_LAYOUT = "2.0.4"
        const val MATERIAL_DESIGN = "1.3.0"
        const val RETROFIT_VERSION = "2.9.0"
    }


     val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${KOTLIN_VERSION}"
     val appCompactX = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
     val ktxCore = "androidx.core:core-ktx:${Versions.KTX_CORE}"
     val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
     val materialDesign = "com.google.android.material:material:${Versions.MATERIAL_DESIGN}"

    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2"//coroutines
    val preferences = "androidx.preference:preference-ktx:1.1.1"// Preference


    // Room
    val roomRuntime = "androidx.room:room-runtime:${Versions.ROOM_DATABASE}" // CHECK AGAIN
    val kaptRoomComplier = "androidx.room:room-compiler:${Versions.ROOM_DATABASE}"


    // Architecture Components
    val lifecycle = "androidx.lifecycle:lifecycle-extensions:2.1.0"//Lifecycle


    // Networking
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    val retrofitRxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.RETROFIT_VERSION}"
    val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT_VERSION}"

    //Rx
    val rxJava = "io.reactivex.rxjava2:rxandroid:2.1.0"
    val rxJavaRxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"

    // Multidex support prior to Android 5.0
    val multidex = "androidx.multidex:multidex:2.0.1"

    //Glide for Image Processing
    val glide = "com.github.bumptech.glide:glide:4.11.0"
    val glideCompiler = "com.github.bumptech.glide:compiler:4.11.0"


    // Koin for Kotlin
    val koinCore = "org.koin:koin-core:$KOIN_VERSION"
    // Koin AndroidX Scope features
    val koinScope = "org.koin:koin-androidx-scope:$KOIN_VERSION"
    // Koin AndroidX ViewModel features
    val koinViewModel = "org.koin:koin-androidx-viewmodel:$KOIN_VERSION"
    // Koin AndroidX Fragment features
    val koinFragment = "org.koin:koin-androidx-fragment:$KOIN_VERSION"
    // Koin AndroidX Experimental features
    val koinExtension = "org.koin:koin-androidx-ext:$KOIN_VERSION"


    //Lottie
    val lottie = "com.airbnb.android:lottie:3.7.0"


    //Exo Player
    val webSocket = "org.java-websocket:Java-WebSocket:1.5.2"

    val philJayGraphUI = "com.github.PhilJay:MPAndroidChart:v3.1.0"
}


object TestLibraries {
    private object Versions {
        const val J_UNIT = "4.13.2"
        const val TEST_RUNNER = "1.0.2"
        const val ESPRESSO = "3.0.2"
    }

    val jUnit = "junit:junit:${Versions.J_UNIT}" // Unit testing
    val jUnitTestRunner = "com.android.support.test:runner:${Versions.TEST_RUNNER}"
    val espressoCore = "com.android.support.test.espresso:espresso-core:${Versions.ESPRESSO}"
}

object NetworkLibrary{
    private object Versions{
        val RETROFIT_VERSION = "2.6.0"
    }

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    val retrofitRxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.RETROFIT_VERSION}"
    val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT_VERSION}"
}


object AndroidSdk {
    const val MINIMUM_SDK = 23
    const val COMPILE_SDK = 30
    const val TARGET_SDK = COMPILE_SDK
}