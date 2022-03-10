#########################################################################################
#                              HOW TO IMPLEMENT buildSrc                                #
#########################################################################################


For learn more :
1.  https://proandroiddev.com/migrate-to-gradle-kotlin-dsl-in-4-steps-f3e3b27e1f4d
2.  https://handstandsam.com/2018/02/11/kotlin-buildsrc-for-better-gradle-dependency-management/



->  Create "buildSrc" directory in root directory

->  Create "build.gradle.kts" file in buildSrc directory

->  Add below code to "build.gradle.kts" file

    repositories {
        jcenter()
    }

    plugins {
        `kotlin-dsl`
    }

    kotlinDslPluginOptions {
        experimentalWarning.set(false)
    }

->  After that "sync now" and "Add dependency" button visible and click on "Add dependency" and after that
    "sync now".

->  Create GradleDependencies.kt file in below hierarchy path
    buildSrc -> src -> main -> kotlin/java -> GradleDependencies.kt

->  After adding GradleDependencies.kt add values regarding gradle file of projects like dependencies,
    version name, version code and other project settings.

->  Now directly update values in "build.gradle" (App level and Project level both) In build.gradle we can directly
    access values of GradleDependencies.
    For eg.
        implementation GradleDependencies.fcm
        implementation GradleDependencies.recyclerview
        implementation GradleDependencies.card_view
