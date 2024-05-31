# 3. Wyszukiwanie najkrotszej sciezki


## Tytuł i nazwa wybranego projektu: 3. Wyszukiwanie najkrotszej sciezki


## Link do Gita: https://github.com/Hokusai97/3.-Wyszukiwanie-najkrotszej-sciezki

### Spis tresci

1. Instrukcja obsługi
2. Zaimplementowane rozwiązania.
2.1 Zaimplementowana rozwiązania w pliku PathFinding.java
2.2 Zaimplementowana rozwiązania w pliku Main.java







1. Instrukcja obsługi:


Wymagane programy/narzędzia do włączenia programu "Wyszukiwanie najkrotszej sciezki":
- Extension Pack for Java - pobieramy ten pakiet w zakładce "Extension" która znajduje się po lewej stronie, wpisujemy "Extension Pack for Java", pobieramy i instalujemy pierwszy wynik wyszukiwania.
- Git - należy przejść na oficjalną stronę Git "https://git-scm.com/" i go pobrać. Po pobraniu, należy uruchomić instalator i zainstalować program. 
- Po pobraniu kodu z repetytorium, należy uruchomić program klikając "Run Java"

Instrukcja obsługi jak użytkować w dalszej kolejności program został zaimplementowany w programie razem z przykładowymi danymi.








2. Zaimplementowana rozwiązania.

W programie zostały zaimplementowane różne rozwiązania, zostaną omówione w podpunkcie 2.1 oraz 2.2 dla osobnych plików PathFidning.java oraz Main.java




2.1 Zaimplementowana rozwiązania w pliku PathFinding.java


PathFinding.java


public static Result findShortestPath(int[][] grid, int rows, int cols) {
    int[][] dp = new int[rows][cols];
    int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    List<int[]>[][] paths = new ArrayList[rows][cols];

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            dp[i][j] = Integer.MAX_VALUE;
            paths[i][j] = new ArrayList<>();
        }
    }
}

Wykorzystuje metode findShortestPath, która znajduje najkrótszą ścieżkę w zadanym dwuwymiarowym gridzie (tablica) 'grid'.
dp - dwuwymiarowa tablica int, w której są przechowywane wartości minimalnych kosztów dojścia do każdej komórki.
directions - tablica dwuwymiarowa int zawierająca możliwe kierunki ruchu, jest możliwe 8 możliwych ruchów (lewo, prawo, góra, dół oraz po skosach).
paths - tablica dwuwymiarowa List<int[]>, w której są przechowywane listy współrzędnych (int[]), reprezentujących ścieżki do każdego elementu.
dp[i][j] jest ustawiane na Integer.MAX_VALUE, co oznacza, że początkowo koszt dojścia do każdej komórki jest nieskończony.
paths[i][j] jest inicjalizowane jako nowa pusta lista ArrayList<>, która będzie przechowywać współrzędne komórek ścieżki do danego elementu.



       for (int i = 0; i < rows; i++) {
            dp[i][0] = grid[i][0];
            paths[i][0].add(new int[]{i, 0});

dp[i][0] jest ustawiane na wartość grid[i][0], oznacza że koszt dojścia do komórek w pierwszej kolumnie jest równy wartościom w odpowiednich komórkach gridu.
paths[i][0].add(new int[]{i, 0}) dodaje współrzędne (i, 0) do listy paths[i][0], reprezentując początkowy punkt ścieżki dla komórek w pierwszej kolumnie.



        for (int j = 1; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                for (int[] dir : directions) {
                    int prevRow = i + dir[0];
                    int prevCol = j + dir[1];

                    if (prevRow >= 0 && prevRow < rows && prevCol >= 0 && prevCol < cols) {
                        if (dp[prevRow][prevCol] != Integer.MAX_VALUE) {
                            int newSum = dp[prevRow][prevCol] + grid[i][j];
                            if (newSum < dp[i][j]) {
                                dp[i][j] = newSum;
                                paths[i][j] = new ArrayList<>(paths[prevRow][prevCol]);
                                paths[i][j].add(new int[]{i, j});
                            }
                        }
                    }
                }
            }
        }

Pętla for (int j = 1; j < cols; j++) iteruje przez kolumny zaczynając od drugiej.
Wewnętrzna pętla for (int i = 0; i < rows; i++) iteruje przez wszystkie wiersze dla bieżącej kolumny.
Pętla for (int[] dir : directions) iteruje przez wszystkie możliwe kierunki ruchu zdefiniowane w tablicy directions.
Wyliczane są współrzędne prevRow i prevCol poprzedniej komórki na podstawie bieżących współrzędnych i kierunku ruchu.
Sprawdzane jest, czy poprzednia komórka jest w granicach gridu oraz czy ma zainicjalizowany koszt różny od Integer.MAX_VALUE.
Jeżeli nowy obliczony koszt newSum jest mniejszy od bieżącego kosztu w dp[i][j], aktualizowany jest koszt w dp[i][j] oraz aktualizowana jest ścieżka w paths[i][j].



        int minSum = Integer.MAX_VALUE;
        List<int[]> minPath = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            if (dp[i][cols - 1] < minSum) {
                minSum = dp[i][cols - 1];
                minPath = paths[i][cols - 1];
            }
        }

        return new Result(minSum, minPath);
    }

minSum jest ustawione na Integer.MAX_VALUE, co oznacza początkowo nieskończony koszt.
minPath jest zainicjalizowane jako nowa pusta lista ArrayList<>, która będzie przechowywać współrzędne komórek ścieżki o najmniejszym koszcie.
Pętla for (int i = 0; i < rows; i++) iteruje przez wszystkie wiersze w ostatniej kolumnie.
Jeśli koszt w dp[i][cols - 1] jest mniejszy niż minSum, aktualizowany jest minSum oraz minPath ustawiane na odpowiednią ścieżkę z paths[i][cols - 1].
Nowy obiekt Result zawierający minSum oraz minPath jest zwracany.








2.2 Zaimplementowana rozwiązania w pliku Main.java



Main.java





       while (true) {
            try {
                System.out.print("Podaj liczbę wierszy: ");
                rows = scanner.nextInt();
                System.out.print("Podaj liczbę kolumn: ");
                cols = scanner.nextInt();
                if (rows <= 0 || cols <= 0) {
                    throw new InputMismatchException("Elementy tablicy muszą być dodatnimi liczbami całkowitymi.");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Proszę wprowadzać wyłącznie dodatnie liczby całkowite.");
                scanner.next(); 

while (true) tworzy nieskończoną pętlę, która będzie trwała do momentu, gdy użytkownik poda poprawne dane.
System.out.print("Podaj liczbę wierszy: "); wyświetla prośbę o podanie liczby wierszy.
rows = scanner.nextInt(); odczytuje wprowadzone przez użytkownika dane jako liczbę całkowitą.
System.out.print("Podaj liczbę kolumn: "); wyświetla prośbę o podanie liczby kolumn.
cols = scanner.nextInt(); odczytuje wprowadzone przez użytkownika dane jako liczbę całkowitą.
if (rows <= 0 || cols <= 0) sprawdza, czy wprowadzone liczby są dodatnie. Jeśli nie, zostaje wyrzucony wyjątek InputMismatchException.
catch (InputMismatchException e) łapie wyjątek, jeśli wprowadziło się dane niezgodnie z oczekiwaniami (np. liczby ujemne, znaki nie będące liczbami).
System.out.println("Proszę wprowadzać wyłącznie dodatnie liczby całkowite."); wyświetla komunikat błędu.
scanner.next(); czyści bufor, aby umożliwić ponowne wprowadzanie danych.



 System.out.println("Podaj elementy tablicy:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                while (true) {
                    try {
                        grid[i][j] = scanner.nextInt();
                        if (grid[i][j] <= 0) {
                            throw new InputMismatchException("Elementy tablicy muszą być dodatnimi liczbami całkowitymi.");
                        }
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Proszę wprowadzać wyłącznie dodatnie liczby całkowite.");
                        scanner.next(); // Oczyść bufor
                    }
                }
            }
        }

Pętla for (int i = 0; i < rows; i++) iteruje przez wiersze tablicy i kolumny tablicy
Pętla while (true) trwa do momentu, gdy użytkownik wprowadzi poprawne dane.
grid[i][j] = scanner.nextInt(); odczytuje wprowadzoną przez użytkownika liczbę całkowitą.
if (grid[i][j] <= 0) sprawdza, czy wprowadzona liczba jest dodatnia. Jeśli nie, wyrzuca wyjątek InputMismatchException.
catch (InputMismatchException e) łapie wyjątek, gdy wprowadzone dane są nieprawidłowe (np. liczby ujemne, znaki nie będące liczbami).




 private static void printInstructions() {
        System.out.println("----------------------------------------------------Witaj----------------------------------------------------");
        System.out.println("                           Znajdujesz się w programie do znajdowania najkrótszej ścieżki.");


Instrukcja użytkowania programu oraz przykład uruchomi się do przeczytania, można skorzystać jeszcze przed rozpoczęciem wpisywania danych po naciśnięciu przycisku Run Java.






