import java.util.*;

public class Main {
    public static void main(String[] args) {
        printInstructions();
        
        Scanner scanner = new Scanner(System.in);
        
        int rows = 0, cols = 0;

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
            }
        }
        
        int[][] grid = new int[rows][cols];

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
                        scanner.next(); 
                    }
                }
            }
        }
        
        PathFinding.Result result = PathFinding.findShortestPath(grid, rows, cols);
        
        System.out.println("Podążając najkrótszą ściężką, minimalna suma wynosi: " + result.sum);
        System.out.print("Sekwencja poruszania się wygląda następująco: ");
        for (int i = 0; i < result.path.size(); i++) {
            System.out.print(grid[result.path.get(i)[0]][result.path.get(i)[1]]);
            if (i < result.path.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        
        scanner.close();
    }
    
    private static void printInstructions() {
        System.out.println("----------------------------------------------------Witaj----------------------------------------------------");
        System.out.println("                           Znajdujesz się w programie do znajdowania najkrótszej ścieżki.");
        System.out.println("                              Program poszukuje najkrótszą ścieżkę od lewej do prawej.");
        System.out.println("                Najkrótsza ścieżka to taka, której suma pól, przez które wiedzie, jest jak najmniejsza!");
        System.out.println("       Kolumna która znajduje się najbardziej po lewej stronie jest startem, a kolumna najbardziej po prawej metą.");
        System.out.println();
        System.out.println();
        System.out.println("                                Poniżej znajdziesz instrukcje obsługi programu.");
        System.out.println();
        System.out.println();
        System.out.println("Instrukcja obsługi programu:");
        System.out.println("1. Podaj liczbę wierszy tablicy.");
        System.out.println("2. Podaj liczbę kolumn tablicy.");
        System.out.println("3. Podaj elementy tablicy wiersz po wierszu.");
        System.out.println("Mozesz podawać dowolone liczby w powyższych 3 elementach. W punkcie 3 musisz podać 4 cyfry oddzielone spacją. Przykład znajdziesz poniżej.");
        System.out.println("Program obliczy najkrótszą ścieżkę i wyświetli wynik.");
        System.out.println("Proszę wprowadzać wyłącznie dodatnie liczby całkowite.");
        System.out.println();
        System.out.println();
        System.out.println("Przykład zastosowania programu:");
        System.out.println("Dane wejściowe:");
        System.out.println("Liczba wierszy: 3");
        System.out.println("Liczba kolumn: 4");
        System.out.println("Podaj elementy tablicy:");
        System.out.println("6 2 5 4");
        System.out.println("3 9 3 2");
        System.out.println("4 4 1 3");
        System.out.println();
        System.out.println();
        System.out.println("Dane wyjściowe:");
        System.out.println("Suma pól: 10");
        System.out.println("Sekwencja: 3, 4, 1, 2");
        System.out.println();
        System.out.println();
        System.out.println("Program się uruchamia. Poniżej rozpoczniesz podawanie cyfr.");
        System.out.println();
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
