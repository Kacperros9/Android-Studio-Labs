# Android-Studio-Labs

Kolekcja mini-projektów tworzonych w ramach laboratoriów z programowania urządzeń mobilnych. Każdy folder zawiera niezależny projekt napisany w języku Kotlin.

## Technologie
* **Język:** Kotlin
* **Środowisko:** Android Studio
* **Android Gradle Plugin (AGP):** Wersja `9.0.0`.
* **Minimum SDK:** API 28 ("Pie"; Android 9.0)

## **Instrukcja uruchomienia lokalnego:**
1. `git clone https://github.com/Kacperros9/Android-Studio-Labs.git`
2. Ważne: Nie otwieraj głównego folderu repozytorium w Android Studio, zamiast tego otwórz **konkretny podkatalog** (np. `lab0102_timer` lub `lab0304_kalkulator`) jako niezależny projekt.
<br>

## 🔄 Cykl życia aktywności (Activity Lifecycle)

Cykl życia aktywności określa etapy, przez które przechodzi ekran aplikacji (Activity), od uruchomienia aż po zamknięcie. System Android sam śledzi te zmiany i automatycznie wywołuje odpowiednie funkcje (callbacki) w odpowiedzi na działania użytkownika. Na przykład podczas minimalizowania, obracania ekranu czy ponownego wznawiania aplikacji.

Główne stany, przez które przechodzi `Activity`:

<img width="511" height="640" alt="Image" src="https://github.com/user-attachments/assets/8dce914f-fcde-471c-a283-dd0da945ffd2" /></br>

`onCreate()` – inicjalizacja widoków i podstawowych danych (wywoływana raz).</br>
`onStart()` – aktywność staje się widoczna dla użytkownika.</br>
`onResume()` – aktywność jest na pierwszym planie i gotowa na interakcję.</br>
`onPause()` – aktywność traci "focus" (np. użytkownik odbiera połączenie), czas na zapisanie lekkich danych.</br>
`onStop()` – aktywność nie jest już widoczna.</br>
`onDestroy()` – całkowite zniszczenie aktywności i zwolnienie zasobów.</br>


## 📁 Realizowane mini-projekty

### Lab0102-Timer

<img src="https://github.com/user-attachments/assets/95b54b72-6129-487a-9d82-1efda3e1a636" width="350" />

### Lab0304-GCD-LCM-Calculator

soon..
