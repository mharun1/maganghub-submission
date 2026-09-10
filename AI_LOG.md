# AI Log Usage

### Claude

- Asked: Make a guideline for making the project and package structure.
- Got: Suggestion on using single module + package by feature.
- Did: Compare the project structure to my last project.

- Asked: Did repository need dependency injection.
- Got: Yes with 2 suggestion approach using Dagger Hilt or manual injection.
- Did: Using manual injection, applying a similar concept to the previously built project.

- Asked: When to use Flow vs suspend fun on Repository layer from this project to the last Jetpack compose built project.
- Got: Explanation of when to use Flow and when to use suspend functions.
- Did: Using a suspend function because the data doesn't need to be re-emitted (POST, UPDATE, DELETE).

- Asked: Difference between ViewModelProvider.Factory vs NewInstanceFactory.
- Got: Explanation of those two concepts.
- Did: Using NewInstanceFactory because those to concepts basically the same.

- Asked: Make user interface for ShowItem.kt
- Got: Full code of composable function for user interface.
- Did: Accepted the code and fix the darker background gradient behind the title that doesn't work.

### Cline

- Asked: Conducted an audit to investigate the cause of slow build times.
- Got: Forgot to assign the fetched API data to the state within the ViewModel, causing the app to be stuck in the Loading state.
- Did: Fixed the issue by assigning the fetched API data to the appropriate state.

- Asked: Investigated a bug cause Serializable to not be detected
- Got: Incorrect dependency declaration in build.gradle.
- Did: Accepted the suggestion given by Cline.

- Asked: Debugged an issue causing test case to fail.
- Got: Left a Log method inside the ViewModel (used for debugging API response), which automatically caused the test cases to fail.
- Did: Removed the debugging Log statement in the ViewModel that was causing the tests to fail automatically.

- Asked: Investigated a bug causing Navigation3 fail to work.
- Got: Incorrect key assign on the Navigation.
- Did: Change the incorrect key to the right key.
