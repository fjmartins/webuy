# How To Build
- Add WEBUY_API_KEY to your local.properties

# Built Using

- MVVM Architecture
- Dagger Hilt
- Retrofit
- Android Jetpack's Navigation
- Glide
- Robolectric
- JUnit

# Reasons for using MVVM:
- UI Components are kept away from the business logic
- Business logic is kept away from database operations
- Easy to understand and read
- A lot less to worry when it comes to managing life cycle events (screen rotations, or if the user leaves the app and comes back hours later)

# How it compares to MVP
- MVVM Uses data binding and is more of an event driven architecture
- In MVP the View is aware of the Presenter
- MVVM can map many views to one ViewModel, the ViewModel has no reference to the View
