# AI Log Usage

### AI Log Overview

| AI Tool | 🗣️ Asked (Task / Inquiry) | 🤖 Got (Result / Suggestion) | ✅ Did (Action Taken) |
| :--- | :--- | :--- | :--- |
| **Claude** | Make a guideline for making the project and package structure. | Suggestion on using single module + package by feature. | Compare the project structure to my last project. |
| **Claude** | Did repository need dependency injection? | Yes, with 2 suggested approaches using Dagger Hilt or manual injection. | Using manual injection, applying a similar concept to the previously built project. |
| **Claude** | When to use `Flow` vs `suspend fun` on Repository layer from this project to the last Jetpack Compose built project. | Explanation of when to use `Flow` and when to use `suspend` functions. | Using a `suspend` function because the data doesn't need to be re-emitted (POST, UPDATE, DELETE). |
| **Claude** | Difference between `ViewModelProvider.Factory` vs `NewInstanceFactory`. | Explanation of those two concepts. | Using `NewInstanceFactory` because those two concepts are basically the same. |
| **Claude** | Make user interface for `ShowItem.kt`. | Full code of composable function for user interface. | Accepted the code and fixed the darker background gradient behind the title that didn't work. |
| **Cline** | Conducted an audit to investigate the cause of slow build times. | Forgot to assign the fetched API data to the state within the `ViewModel`, causing the app to be stuck in the Loading state. | Fixed the issue by assigning the fetched API data to the appropriate state. |
| **Cline** | Investigated a bug causing `Serializable` to not be detected. | Incorrect dependency declaration in `build.gradle`. | Accepted the suggestion given by Cline. |
| **Cline** | Debugged an issue causing test case to fail. | Left a `Log` method inside the `ViewModel` (used for debugging API response), which automatically caused the test cases to fail. | Removed the debugging `Log` statement in the `ViewModel` that was causing the tests to fail automatically. |
| **Cline** | Investigated a bug causing `Navigation3` to fail to work. | Incorrect key assigned on the Navigation. | Changed the incorrect key to the right key. |
