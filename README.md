# ChatApp

## Architecture

Clean Architecture + MVVM, split into separate Gradle modules.

**`:app`** — application entry point. Contains MyApplication and all Koin wiring. Depends on :composeApp, :data, and :
domain — it's the only module in the graph that sees all layers.

**`:domain`** — models, repository interfaces, use cases. No framework dependencies, no dependency on any other module.
Pure Kotlin, shared across platforms via KMP.

**`:data`** — repository implementations, Room database, in-memory user store, Koin wiring. Depends only on `:domain`.

**`:composeApp`** — presentation. Depends only on `:domain`

`:domain` and `:data` are KMP modules. Only Android is wired up for now.

---

## Current State

Two hardcoded users — John and Sarah. Switch between them with the icon in the top bar. Messages are persisted locally
in Room.

---

## What's Next

- **UI mapper** — section header grouping and bubble spacing logic currently sit in the ViewModel. These are display
  decisions and should live in a dedicated mapper.
- **Smarter auto-scroll** — only jump to the latest message if the user is already at the bottom. Don't interrupt them
  if they're scrolling through history.
- **Compose Navigation** — wire up proper navigation so `MessageViewModel` receives the other user's id as a constructor
  argument rather than always defaulting to the hardcoded other user.
- **User avatars** — load profile images in the top bar via Coil.
- **Message pagination** — loading the entire history at once would cause performance issues.
- **Error handling** — show failures in the UI instead of only logging them.
- **Loading state** — show a spinner or skeleton while the initial message list is being fetched.
- **Design system** — define full light/dark colour palettes, typography, and component styles.
- **ProGuard/R8 setup** — include any necessary rules.
- **Unit tests** — ViewModels, use cases, mapper, repository implementations.
- **Cleanup** — run Android Lint, remove unused code and resources, update all dependencies to the latest stable
  version.