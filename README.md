# Maganghub Submission (TV Maze)

## Prerequisites

- Android Studio (Ladybug or newer)
- Minimum SDK 24

## How to Run

1. Clone the repository.
2. Open the project in Android Studio and wait for the Gradle sync to complete.
3. Run using an emulator or physical device via the **Run** button (▶) or `Shift + F10`.

## Running Tests

```bash
./gradlew test
```

---

## Architecture Decisions

This app uses **MVVM** with a **single module + package-by-feature** approach. For a scope of 2 screens (List & Detail) within a 1-day timeframe, a multi-module setup would be overkill. Package-by-feature still separates concerns per feature without the overhead of setting up separate modules.

Some specific decisions:

- **DTO separated from the domain model** via a mapper (`ShowDto.toDomain()`), so changes in the API's JSON structure don't directly impact the UI, and null-handling (`rating.average`) is centralized in one place.
- **Repository uses `suspend fun`, not `Flow`** — the TVMaze API is a one-shot REST call, not a reactive data source like Room/DataStore.
- **Manual DI over Hilt** — chosen to avoid setup overhead under a 1-day time constraint. Dependencies are still injected via constructor (`ShowRepository` into the ViewModel), so testability isn't compromised.
- **State managed as a sealed class** (`Loading` / `Success` / `Error`) exposed via `StateFlow`, implementing Unidirectional Data Flow. The ViewModel becomes the single source of truth, the UI simply observes & renders — this also simplifies unit testing (just assert `StateFlow.value` without mocking the Android framework).

---

## What I'd Improve with More Time

1. **Pagination** on the `Home` screen, so a large number of shows/movies aren't loaded all at once — this would also speed up the app build process.
2. **Search bar** to make it easier for users to find the show/movie they want without manually scrolling.
3. **Sorting** (e.g. by rating, alphabetically) on the `Home` screen to help users find shows/movies that match their preferences.
4. **Optional detail info** (season, episode, cast, genre) on the detail screen, per the bonus points in the requirements.
5. **Migrate from manual DI to Dagger Hilt** for easier dependency injection as the app grows.
6. **Room for local caching**, so users can still view the latest data while offline.

---

Demonstration Video Link:
https://drive.google.com/file/d/1xdmLXdlP21oGSm_TogzFAB3X-fdl15b-/view?usp=drivesdk
