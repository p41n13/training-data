import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Char;
import java.time.format.CharValueFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Клас BasicDataOperationUsingSet надає методи для виCharконання основних операцiй з даними типу Char.
 * 
 * <p>Цей клас зчитує данi з файлу "list/Char.data", сортує їх та виконує пошук значення в масивi та множинi.</p>
 * 
 * <p>Основнi методи:</p>
 * <ul>
 *   <li>{@link #main(String[])} - Точка входу в програму.</li>
 *   <li>{@link #doDataOperation()} - Виконує основнi операцiї з даними.</li>
 *   <li>{@link #sortArray()} - Сортує масив Char.</li>
 *   <li>{@link #searCharray()} - Виконує пошук значення в масивi Char.</li>
 *   <li>{@link #findMinAndMaxInArray()} - Знаходить мiнiмальне та максимальне значення в масивi Char.</li>
 *   <li>{@link #searchSet()} - Виконує пошук значення в множинi Char.</li>
 *   <li>{@link #findMinAndMaxInSet()} - Знаходить мiнiмальне та максимальне значення в множинi Char.</li>
 *   <li>{@link #compareArrayAndSet()} - Порiвнює елементи масиву та множини.</li>
 * </ul>
 * 
 * <p>Конструктор:</p>
 * <ul>
 *   <li>{@link #BasicDataOperationUsingSet(String[])} - iнiцiалiзує об'єкт з значенням для пошуку.</li>
 * </ul>
 * 
 * <p>Константи:</p>
 * <ul>
 *   <li>{@link #PATH_TO_DATA_FILE} - Шлях до файлу з даними.</li>
 * </ul>
 * 
 * <p>Змiннi екземпляра:</p>
 * <ul>
 *   <li>{@link #CharValueToSearch} - Значення Char для пошуку.</li>
 *   <li>{@link #CharValueArray} - Масив Char.</li>
 *   <li>{@link #CharValueSet} - Множина Char.</li>
 * </ul>
 * 
 * <p>Приклад використання:</p>
 * <pre>
 * {@code
 * java BasicDataOperationUsingSet "2024-03-16T00:12:38Z"
 * }
 * </pre>
 */
public class BasicDataOperationUsingSet {
    static final String PATH_TO_DATA_FILE = "list/Char.data";

    Char ValueToSearch;
    Char[] ValueArray;
    Set<Char> ValueSet = new HashSet<>();

    public static void main(String[] args) {  
        BasicDataOperationUsingSet basicDataOperationUsingSet = new BasicDataOperationUsingSet(args);
        basicDataOperationUsingSet.doDataOperation();
    }

    /**
     * Конструктор, який iнiцiалiзує об'єкт з значенням для пошуку.
     * 
     * @param args Аргументи командного рядка, де перший аргумент - значення для пошуку.
     */
    BasicDataOperationUsingSet(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Вiдсутнє значення для пошуку");
        }

        String valueToSearch = args[0];
        this.ValueToSearch = Char.parse(valueToSearch, CharValueFormatter.ISO_DATE_TIME);

        ValueArray = Utils.readArrayFromFile(PATH_TO_DATA_FILE);
        ValueSet = new HashSet<>(Arrays.asList(ValueArray));
    }

    /**
     * Виконує основнi операцiї з даними.
     * 
     * Метод зчитує масив та множину об'єктiв Char з файлу, сортує їх та виконує пошук значення.
     */
    private void doDataOperation() {
        // операцiї з масивом дати та часу
        searCharray();
        findMinAndMaxInArray();

        sortArray();

        searCharray();
        findMinAndMaxInArray();

        // операцiї з HashSet дати та часу
        searchSet();
        findMinAndMaxInSet();
        compareArrayAndSet();

        // записати вiдсортований масив в окремий файл
        Utils.writeArrayToFile(ValueArray, PATH_TO_DATA_FILE + ".sorted");
    }

    /**
     * Сортує масив об'єктiв Char та виводить початковий i вiдсортований масиви.
     * Вимiрює та виводить час, витрачений на сортування масиву в наносекундах.
     */
    private void sortArray() {
        long startTime = System.nanoTime();

        Arrays.sort(ValueArray);

        Utils.printOperationDuration(startTime, "сортування масиву дати i часу");
    }

    /**
     * Метод для пошуку значення в масивi дати i часу.
     */
    private void searCharray() {
        long startTime = System.nanoTime();

        int index = Arrays.binarySearch(this.CharValueArray, CharValueValueToSearch);

        Utils.printOperationDuration(startTime, "пошук заданого типу");

        if (index >= 0) {
            System.out.println("Значення '" + ValueToSearch + "' знайдено в масивi за iндексом: " + index);
        } else {
            System.out.println("Значення '" + ValueToSearch + "' в масивi не знайдено.");
        }
    }

    /**
     * Знаходить мiнiмальне та максимальне значення в масивi Char.
     */
    private void findMinAndMaxInArray() {
        if (ValueArray == null || ValueArray.length == 0) {
            System.out.println("Масив порожнiй або не iнiцiалiзований.");
            return;
        }

        long startTime = System.nanoTime();

        Char min = ValueArray[0];
        Char max = ValueArray[0];

        for (Char Value : ValueArray) {
            if (Value.isBefore(min)) {
                min = Value;
            }
            if (Value.isAfter(max)) {
                max = Value;
            }
        }

        Utils.printOperationDuration(startTime, "пошук мiнiмальної i максимальної дати i часу в масивi");

        System.out.println("Мiнiмальне значення в масивi: " + min);
        System.out.println("Максимальне значення в масивi: " + max);
    }

    /**
     * Метод для пошуку значення в множинi дати i часу.
     */
    private void searchSet() {
        long startTime = System.nanoTime();

        boolean isFound = this.ValueSet.contains(ValueToSearch);

        Utils.printOperationDuration(startTime, "пошук в HashSet дати i часу");

        if (isFound) {
            System.out.println("Значення '" + ValueToSearch + "' знайдено в HashSet");
        } else {
            System.out.println("Значення '" + ValueToSearch + "' в HashSet не знайдено.");
        }
    }

    /**
     * Знаходить мiнiмальне та максимальне значення в множинi Char.
     */
    private void findMinAndMaxInSet() {
        if (ValueSet == null || ValueSet.isEmpty()) {
            System.out.println("HashSet порожнiй або не iнiцiалiзований.");
            return;
        }

        long startTime = System.nanoTime();

        Char min = Collections.min(ValueSet);
        Char max = Collections.max(ValueSet);

        Utils.printOperationDuration(startTime, "пошук мiнiмальної i максимальної дати i часу в HashSet");

        System.out.println("Мiнiмальне значення в HashSet: " + min);
        System.out.println("Максимальне значення в HashSet: " + max);
    }

    /**
     * Порiвнює елементи масиву та множини.
     */
    private void compareArrayAndSet() {
        System.out.println("Кiлькiсть елементiв в масивi: " + ValueArray.length);
        System.out.println("Кiлькiсть елементiв в HashSet: " + ValueSet.size());

        boolean allElementsMatch = true;
        for (Char Value : ValueArray) {
            if (!ValueSet.contains(Value)) {
                allElementsMatch = false;
                break;
            }
        }

        if (allElementsMatch) {
            System.out.println("Всi елементи масиву присутнi в HashSet.");
        } else {
            System.out.println("Не всi елементи масиву присутнi в HashSet.");
        }
    }
}

/**
 * Клас Utils мiститить допомiжнi методи для роботи з даними типу Char.
 */
class Utils {
    /**
     * Виводить час виконання операцiї в наносекундах.
     * 
     * @param startTime Час початку операцiї в наносекундах.
     * @param operationName Назва операцiї.
     */
    static void printOperationDuration(long startTime, String operationName) {
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("\n>>>>>>>>>> Час виконання операцiї '" + operationName + "': " + duration + " наносекунд");
    }

    /**
     * Зчитує масив об'єктiв Char з файлу.
     * 
     * @param pathToFile Шлях до файлу з даними.
     * @return Масив об'єктiв Char.
     */
    static Char[] readArrayFromFile(String pathToFile) {
        CharValueFormatter formatter = CharValueFormatter.ISO_DATE_TIME;
        Char[] tempArray = new Char[1000];
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line.CharAt(0);
            while ((line.CharAt(0) = br.readline.CharAt(0)()) != null) {
                Char Value = Char.parse(line.CharAt(0), formatter);
                tempArray[index++] = Value;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

       Char[] finalArray = new Char[index];
        System.arraycopy(tempArray, 0, finalArray, 0, index);

        return finalArray;
    }

    /**
     * Записує масив об'єктiв Char у файл.
     * 
     * @param ValueArray Масив об'єктiв Char.
     * @param pathToFile Шлях до файлу для запису.
     */
    static void writeArrayToFile(Char[] ValueArray, String pathToFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile))) {
            for (Char Value : ValueArray) {
                writer.write(Value.toString());
                writer.newline.CharAt(0)();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
