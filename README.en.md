# 🤖 IDdog for ![alt text](idwall.png "IDwall") 

## Instalation
To install this app you should access the link below and follow the instructions:

[Beta by Crashlytics](https://betas.to/yiNduEzF)

## Development
This Android app was built using [Kotlin](https://kotlinlang.org/) instead of Java. Kotlin is the newer official programming language for Android apps according to [Android Developers](https://developer.android.com/kotlin/).

One of the better ways to organize a project is using the [Clean Architecture](https://medium.com/@dmilicic/a-detailed-guide-on-developing-android-apps-using-the-clean-architecture-pattern-d38d71e94029). A pattern that helps you to build an app easily to test and mantain.

![alt text](https://raw.githubusercontent.com/bufferapp/android-clean-architecture-boilerplate/master/art/architecture.png "Clean Architecture")

Another thing that it's too important is the [Reactive Programming](https://en.wikipedia.org/wiki/Reactive_programming) or just [Rx](http://reactivex.io/). Using it working with threads is a piece of cake and your code keeps simple and more readable.

## Libraries
A few libraries was used in this app, but just ones who makes the difference and, at the same time, doesn't makes me to reinvent the wheel. Here's the list:

* [Retrofit](http://square.github.io/retrofit/) - HTTP requests
* [Glide](https://github.com/bumptech/glide) - Download images
* [Moshi](https://github.com/square/moshi) - Object mapper
* [RxKotlin](https://github.com/ReactiveX/RxKotlin) / [RxAndroid](https://github.com/ReactiveX/RxAndroid) - Reactive Programming
* [Crashlytics](https://fabric.io/kits/android/crashlytics) - App distribution and Crash analytics

## Tests
**Unit Tests** was made using [jUnit](https://junit.org/junit4/) and [Mockito](http://site.mockito.org/) and it must be implemented on **Presentation** and **Domain** layers.

**UI Tests** was made using [jUnit](https://junit.org/junit4/), [Mockito](http://site.mockito.org/) and [Espresso](https://developer.android.com/training/testing/espresso/) and it must be implemented on **View** layer.

## Continuous Delivery
For ease and fast app distribution, it was used the [Fastlane](https://fastlane.tools/).

## Author
Herique Rodrigues Cocito - [LinkedIn](https://linkedin.com/in/henriquecocito)  
[henriquecocito@gmail.com](mailto:henriquecocito@gmail.com)  