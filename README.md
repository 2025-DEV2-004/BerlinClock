# BerlinClock

This is a simple implementation of the Berlin Clock in Kotlin. The Berlin Clock is a unique
timekeeping device that displays time using a combination of colored lights.
The code is also foreseen of a Android Application that can be used to display the time in a Berlin
Clock format.
In order to run the Android application, you need to have Android Studio installed and set up.

## Preview :

[![](../BerlinClock/Screenshot_20250716_152113.png)](https://github.com/2025-DEV2-004/BerlinClock/blob/main/Screenshot_20250716_152113.png)

### Tests :

to run the test use the command :

./gradlew app:testDebugUnitTest in your terminal

### Building the project :

to build the project use the command :
./gradlew app:assemble
You can find the generated APK in "../app/build/outputs/apk"
to install the app on your device use the command :
./gradlew app:installDebug

Once the app is installed, you can open it and it will display the current time in the Berlin Clock
format.
