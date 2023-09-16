Temat: Aplikacja do zapisywania i udostępniania wartych uwagi informacji, 
np. strony www, listy zakupów, zaproszenia na spotkanie, notatki.

Skład grupy: 
Maciej Sobolewski. 
Mateusz Pogrebniak.

Wykorzystane technologii:
+Java 17
+Spring Boot
+Spring Security
+Thymeleaf
+Relacyjna Baza H2

Główne funkcjonalności:
+Dane przechowywane w relacyjnej bazie.
+Dostęp do danych poprzez pulę połączeń skonfigurowaną na serwerze aplikacyjnym.
+Podział na warstwy (osobne komponenty dla warstwy danych - wzorzec DAO, logiki i prezentacji, komunikacja między warstwami z użyciem interfejsów), komponenty warstwy danych i logiki powinny działać w kontenerze serwera aplikacyjnego (EJB lub CDI), warstwa prezentacji może być aplikacją webową lub aplikacją kliencką (konsolową lub z GUI działającą jako stand-alone client application w kontenerze aplikacji klienckich).
+Obsługa uwierzytelniania
+/-Asynchroniczne przetwarzanie (np.: z użyciem JMS)
+Dziennik zdarzeń.
+Testy jednostkowe.

Funkcjonalność:
+Różne role użytkowników: LIMITED_USER, FULL_USER, ADMIN
+Rejestracja i Logowanie
+/-Sesja i ciasteczka
+Administrator może zarządzać użytkownikami (np. zmiana roli)
+Przeglądanie/dodawanie/edycja/usuwanie informacji.
+Udostępnianie informacji innym użytkownikom.
+Filtrowanie i wyszukiwanie informacji (po Dacie i Kategorii).
+Sortowanie informacji (w obu kierunkach) (po: Dacie, Kategorii, Alfabetycznie)
+Wyszukiwanie kategorii.
+Przeglądanie/dodawanie/edycja/usuwanie kategorii.
+Walidacja formularzy: Logowanie, Rejestracja, Informacja, Kategoria

Instalacja: Aplikacja jest pakowana do .war, a następnie odpalana na serwerze.