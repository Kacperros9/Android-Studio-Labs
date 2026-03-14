# Android-Studio-Labs

Kolekcja mini-projektów tworzonych w ramach laboratoriów z programowania urządzeń mobilnych. Każdy folder zawiera niezależny projekt napisany w języku Kotlin.

## Technologie
* **Język:** Kotlin
* **Środowisko:** Android Studio
* **Minimum SDK:** API 28 ("Pie"; Android 9.0)

---

## 🔄 Cykl życia aktywności (Activity Lifecycle)

Cykl życia aktywności określa etapy, przez które przechodzi ekran aplikacji (Activity), od uruchomienia aż po zamknięcie. System Android sam śledzi te zmiany i automatycznie wywołuje odpowiednie funkcje (callbacki) w odpowiedzi na działania użytkownika. Na przykład podczas minimalizowania, obracania ekranu czy ponownego wznawiania aplikacji.

Poniższy schemat przedstawia główne stany, przez które przechodzi `Activity`:

<img width="511" height="640" alt="Image" src="https://github.com/user-attachments/assets/8dce914f-fcde-471c-a283-dd0da945ffd2" />


* `onCreate()` – inicjalizacja widoków i podstawowych danych (wywoływana raz).
* `onStart()` – aktywność staje się widoczna dla użytkownika.
* `onResume()` – aktywność jest na pierwszym planie i gotowa na interakcję.
* `onPause()` – aktywność traci "focus" (np. użytkownik odbiera połączenie), czas na zapisanie lekkich danych.
* `onStop()` – aktywność nie jest już widoczna.
* `onDestroy()` – całkowite zniszczenie aktywności i zwolnienie zasobów.

---
