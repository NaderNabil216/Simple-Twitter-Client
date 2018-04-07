# Twitter Client


A Simple Twitter Client Application that is integrated with Twitter4j SDK, 
implementing MVP Model and using a lot of Great Libraries.


<img src="https://i.imgur.com/DpE6UOW.jpg" width="240" height="427"/> <img src="https://i.imgur.com/D8IJbED.jpg" width="240" height="427"/> <img src="https://i.imgur.com/bYcmDh5.jpg" width="240" height="427"/>

<br>

<img src="https://i.imgur.com/Pv1jGCW.jpg" width="240" height="427"/> <img src="https://i.imgur.com/ZCP8h2n.jpg" width="240" height="427"/> <img src="https://i.imgur.com/mxI1tE5.jpg" width="240" height="427"/>

-----------------------------------------------------------------------------------------------------

# Application Features
1. **Log with** your Twitter Account
2. **Get the user followers** in a Material Design Recycler View with **Pull to Refresh** and **Infinite Scrolling**.
3. Followers List works in **offline mode**.
4. **Get Followers Infromation** in a great User Experience Screen with **Sticky Header** for Followers Background Image.
5. Followers Information works in **offline mode**
6. **Localization** (Arabic to English and vice versa).

# Libraries used
1. [Piccaso](http://androidgifts.com/picasso-android-library-tutorial/)
2. [Rosetta ](https://github.com/ahmedaljazzar/rosetta)
3. [CircleImageView ](https://github.com/hdodenhof/CircleImageView)
4. [Google Gson ](https://github.com/google/gson)
5. [Twitter4J ](http://twitter4j.org/en/index.html)

# Software Architectural Pattern used
 [MVP (Model–View–Presenter)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter)
 
# Design Pattern
 [Singleton](https://en.wikipedia.org/wiki/Singleton_pattern)

-----------------------------------------------------------------------------------------------------
# User Documentation :

1.**Login Screen :**

User can press on Login in button to Log in the application via your Twitter Account.  A Webpage will be opened to granted auth. 

2.**Followers Screen :**

Followers will appear realted to the logged user. Pull to Refresh and Infinite Scrolling are applied.

3.**Follower Details Screen :**

Details that related to each user will be appeared with his/her recent 20 tweets that is done by this specific user.

4.**User Profile Screen :**

Current User Screen That user can change language with or add new account or select other account or logout .

5.**Logout :**

If User Press Logout , all cached data will be deleted and screen will be redirect to Login screen .

6.**Change Langauge:**

Application supports Arabic and English Language.

7.**Caching :**

Local Database is applied in these three screens Followers List , Follower Profile and User Profile using SQLite DB.

-----------------------------------------------------------------------------------------------------
# Technical Documentation :
1. **Piccaso :**

Images add much-needed context and visual flair to Android applications. Picasso allows for hassle-free image loading in your application—often in one line of code!

Many common pitfalls of image loading on Android are handled automatically by Picasso:
- Handling ImageView recycling and download cancelation in an adapter.
- Complex image transformations with minimal memory use.
- Automatic memory and disk caching.

[This tutorial is very helpful](http://androidgifts.com/picasso-android-library-tutorial/), showing you how to user Piccaso Library in the best way.

2. **Rosetta**

Rosetta is an Android library that helps your app supporting multiple languages and switching between them without causing any headaches.

3. **CircleImageView**

A fast circular ImageView perfect for profile images. This is based on RoundedImageView from Vince Mi which itself is based on techniques recommended by Romain Guy.

4. **Google Gson**

Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object. Gson can work with arbitrary Java objects including pre-existing objects that you do not have source-code of.

There are a few open-source projects that can convert Java objects to JSON. However, most of them require that you place Java annotations in your classes; something that you can not do if you do not have access to the source-code. Most also do not fully support the use of Java Generics. Gson considers both of these as very important design goals.

5. **Twitter4j**

Twitter4J is an open source Java library, which provides a convenient API for accessing the Twitter API.

Simply put, here’s how we can interact with the Twitter API; we can:

Post a tweet
Get timeline of a user, with a list of latest tweets
Send and receive direct messages
Search for tweets and much more
This library ensures that we can easily do these operations, and it also ensures the security and privacy of a user – for which we naturally need to have OAuth credentials configured in our app

-----------------------------------------------------------------------------------------------------

# MVP Software Architecture Pattern :

**What is MVP?**

The MVP pattern allows separate the presentation layer from the logic, so that everything about how the interface works is separated from how we represent it on screen. Ideally the MVP pattern would achieve that same logic might have completely different and interchangeable views.

First thing to clarify is that MVP is not an architectural pattern, it’s only responsible for the presentation layer . In any case it is always better to use it for your architecture that not using it at all.

**Why use MVP?**

In Android we have a problem arising from the fact that Android activities are closely coupled to both interface and data access mechanisms. We can find extreme examples such as CursorAdapter, which mix adapters, which are part of the view, with cursors, something that should be relegated to the depths of data access layer .

For an application to be easily extensible and maintainable we need to define well separated layers. What do we do tomorrow if, instead of retrieving the same data from a database, we need to do it from a web service? We would have to redo our entire view .

MVP makes views independent from our data source. We divide the application into at least three different layers, which let us test them independently. With MVP we are able to take most of logic out from the activities so that we can test it without using instrumentation tests.

**How to implement MVP for Android**

Well, this is where it all starts to become more diffuse. There are many variations of MVP and everyone can adjust the pattern idea to their needs and the way they feel more comfortable. The pattern varies depending basically on the amount of responsibilities that we delegate to the presenter.

Is the view responsible to enable or disable a progress bar, or should it be done by the presenter? And who decides which actions should be shown in the Action Bar? That’s where the tough decisions begin . I will show how I usually work, but I want this article to be more a place for discussion that strict guidelines on how to apply MVP, because up to know there is no “standard” way to implement it .

**The presenter**


The presenter is responsible to act as the middle man between view and model. It retrieves data from the model and returns it formatted to the view. But unlike the typical MVC, it also decides what happens when you interact with the view.

**The View**

The view, usually implemented by an Activity (it may be a Fragment, a View… depending on how the app is structured), will contain a reference to the presenter. Presenter will be ideally provided by a dependency injector such as Dagger, but in case you don’t use something like this, it will be responsible for creating the presenter object. The only thing that the view will do is calling a method from the presenter every time there is an interface action (a button click for example).

**The model**

In an application with a good layered architecture, this model would only be the gateway to the domain layer or business logic. If we were using the Uncle Bob clean architecture , the model would probably be an interactor that implements a use case. But this is another topic that I’d like to discuss in future articles. For now, it is enough to see it as the provider of the data we want to display in the view.

-----------------------------------------------------------------------------

# Singleton Design Pattern

The singleton pattern is one of the simplest design patterns. Sometimes we need to have only one instance of our class for example a single DB connection shared by multiple objects as creating a separate DB connection for every object may be costly. Similarly, there can be a single configuration manager or error manager in an application that handles all problems instead of creating multiple managers.

Definition:
The singleton pattern is a design pattern that restricts the instantiation of a class to one object.
Let’s see various design options for implementing such a class. If you have a good handle on static class variables and access modifiers this should not be a difficult task.

-----------------------------------------------------------------------------


# References

1.[Ahmed adel](https://github.com/ahmed-adel-said)

2.[benidict1995](https://github.com/benidict1995/Android-MVP-SQLite)

3.[Android Hive](https://www.androidhive.info/2012/09/android-twitter-oauth-connect-tutorial/)


