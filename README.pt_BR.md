# 🤖 IDdog para ![alt text](idwall.png "IDwall") 

## Instalação
Para instalar o app você precisa acessar o link abaixo e seguir as instruções:

[Beta by Crashlytics](https://betas.to/yiNduEzF)

Ou faça o download do apk [aqui](https://github.com/henriquecocito/iddog/blob/master/app-debug.apk)

## Desenvolvimento
Esse app foi desenvolvido utilizando [Kotlin](https://kotlinlang.org/) ao invés de Java. Kotlin é a mais nova linguagem de programação utilizada para aplicativos Android, segundo o site [Android Developers](https://developer.android.com/kotlin/).

Uma das melhores formas de se organizar o projeto é utilizando a [Clean Architecture](https://medium.com/@dmilicic/a-detailed-guide-on-developing-android-apps-using-the-clean-architecture-pattern-d38d71e94029). São boas práticas que ajudam a construir um app que seja fácil de testar e de fazer manutenção.

![alt text](https://raw.githubusercontent.com/bufferapp/android-clean-architecture-boilerplate/master/art/architecture.png "Clean Architecture")

Uma outra coisa que é muito importante é a [Programação Reativa](https://en.wikipedia.org/wiki/Reactive_programming) ou simplesmente [Rx](http://reactivex.io/). É muito fácil trabalhar com threads quando se utiliza o Rx, além disso o código fica mais simples e mais fácil de se ler.

## Bibliotecas
Algumas bibliotecas foram usadas nesse app, mas só aquelas que fazem a diferença e, ao mesmo tempo, não me fazem reinventar a roda. Segue a lista:

* [Retrofit](http://square.github.io/retrofit/) - Requisições HTTP
* [Glide](https://github.com/bumptech/glide) - Download de imagens
* [Moshi](https://github.com/square/moshi) - Object mapper
* [RxKotlin](https://github.com/ReactiveX/RxKotlin) / [RxAndroid](https://github.com/ReactiveX/RxAndroid) - Programação reativa
* [Crashlytics](https://fabric.io/kits/android/crashlytics) - Distribuição do app e análise de crashes

## Testes
**Testes unitários** foram feitos utilizando [jUnit](https://junit.org/junit4/) e [Mockito](http://site.mockito.org/) e devem ser implementados nas camadas **Presentation** e **Domain**.

**Testes de UI** foram feitos utilizando [jUnit](https://junit.org/junit4/), [Mockito](http://site.mockito.org/) e [Espresso](https://developer.android.com/training/testing/espresso/) e devem ser feitos na camada **View**.

## Entrega contínua
Para facilitar e agilizar a distribuição do app, foi configurado e utilizado o [Fastlane](https://fastlane.tools/).

## Author
Herique Rodrigues Cocito - [LinkedIn](https://linkedin.com/in/henriquecocito)  
[henriquecocito@gmail.com](mailto:henriquecocito@gmail.com)  