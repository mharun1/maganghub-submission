# AI Code Review Exercise — Issue List

## Findings

| No | Category | Issue |
|----|----------|-------|
| 1 | **Threading** | `loadMovies` is not called inside `viewModelScope.launch`, which can cause the app to block (freeze) and potentially trigger an **ANR** (Application Not Responding) exception. |
| 2 | **Error Handling** | There is no `try/catch` block for error handling, so data fetch failures from the API/network can crash the app. |
| 3 | **State Management** | No handling for `Loading` and `Error` states exposed to the UI. |
| 4 | **Separation of Concerns** | The data fetching logic is implemented in the ViewModel; it should be placed in the **repository layer**. |
| 5 | **Reactivity** | A plain `List` is used instead of `StateFlow`/`LiveData`, which prevents Compose from detecting data changes and triggering recomposition. |

##
