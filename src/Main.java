import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        // Используем конструкцию try-with-resources для закрытия BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
            int maxRow = 0;
            int minSeatInMaxRow = Integer.MAX_VALUE;
            int previousRow = 0;
            int previousSeat = 0;
            String line;

            // Чтение строк из файла
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" "); // Разделение строки на части
                int currentRow = Integer.parseInt(parts[0]);   // Текущий ряд
                int currentSeat = Integer.parseInt(parts[1]);  // Текущее место
                // Проверка условия для определения пары мест
                if (previousRow == currentRow && previousSeat == currentSeat - 3) {
                    // Если найден ряд с большим номером
                    if (currentRow > maxRow) {
                        maxRow = currentRow;                // Обновляем максимальный ряд
                        minSeatInMaxRow = previousSeat + 1; // Обновляем минимальное место в максимальном ряду
                    } else if (currentRow == maxRow) {
                        // Если ряд равен максимальному, обновляем минимальное место
                        minSeatInMaxRow = Math.min(minSeatInMaxRow, previousSeat + 1);
                    }
                }

                // Обновление предыдущих значений
                previousRow = currentRow;
                previousSeat = currentSeat;
            }

            // Вывод результата
            System.out.println("Максимальный ряд: " + maxRow);
            System.out.println("Минимальный номер места в максимальном ряду: " + minSeatInMaxRow);
        } catch (Exception e) {
            // Вывод сообщения об ошибке, если что-то пошло не так
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
