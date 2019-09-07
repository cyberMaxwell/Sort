package main;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SortMain {

    public static void main(String[] args) {
        System.out.println("Sort 2019 Maksim Efanov 8311: 2019-09-06\n");
        int count = args.length;

        if (count == 0)
            System.out.println("Использование: Sort.exe /?\n");
        else if (args[0].equals("/?")) {
            System.out.println("Использование: Sort.exe ВходнойФайл [ВыходнойФайл] [/<сортировка>] [/<тип сортировки>]\n");
            System.out.println("<сортировка>\n  ascending - по умолчанию\n  descending\n");
            System.out.println("<тип сортировки>\n  tree - по умолчанию\n  selection\n");
        } else if (args[0].startsWith("/")) {
            System.out.println("Вы не указали входной файл");
        } else if (count == 4 && (!args[2].startsWith("/") || !args[3].startsWith("/"))) {
            System.out.println("Неверные параметры\n\nИспользование: Sort.exe /?");
        } else if (count == 3 && !args[2].startsWith("/")) {
            System.out.println("Неверные параметры\n\nИспользование: Sort.exe /?");
        } else if (count > 4) {
            System.out.println("Слишком много аргументов\n\nИспользование: Sort.exe /?");
        } else {
            String inputFileName, outputFileName, methodOfSort, typeOfSort;

            Pattern pattern = Pattern.compile("[:\\+\\?]+");
            Matcher matcher = pattern.matcher(args[1]);

            inputFileName = args[0];
            if (count >= 2 && !args[1].contains("/")) {
                if (matcher.find()) {
                    System.out.println("Недопустимый символ в имени выходного файла");
                    System.exit(0);
                }
                outputFileName = args[1];
            } else {
                outputFileName = inputFileName + ".sort";
            }
            System.out.println(args[1]);

                methodOfSort = "ascending";
                typeOfSort = "tree";

                for (int i = 1; i < count; i++) {
                    if (args[i].equals("/ascending")) {
                        methodOfSort = "ascending";
                        //outputFileName = inputFileName + ".sort";
                    } else if (args[i].equals("/descending")) {
                        methodOfSort = "descending";
                        //outputFileName = inputFileName + ".sort";
                    } else if (args[i].equals("/tree")) {
                        typeOfSort = "tree";
                        //outputFileName = inputFileName + ".sort";
                        //methodOfSort = "ascending";
                    } else if (args[i].equals("/selection")) {
                        typeOfSort = "selection";
                        //outputFileName = inputFileName + ".sort";
                        //methodOfSort = "ascending";
                    } else if (args[i].startsWith("/")) {
                        System.out.println("Выходной файл не может начинаться со знака /\n\nИспользование: Sort.exe /?");
                        System.exit(0);
                    }// else outputFileName = args[i];
                }

                System.out.println(inputFileName);
                System.out.println(outputFileName);
                System.out.println(methodOfSort);
                System.out.println(typeOfSort);
                Sorting(inputFileName, outputFileName, methodOfSort, typeOfSort);

            }
        }

    public static void Sorting(String inputFileName, String outputFileName, String methodOfSort, String typeOfSort) {
        try {

            File file = new File(inputFileName);
            FileReader fileReader = new FileReader(inputFileName);
            FileWriter fileWriter = new FileWriter(outputFileName);

            int lastElement = (int)file.length();
            int array[] = new int[lastElement];

            for(int i = 0; i <= lastElement - 1; i++) {
                array[i] = fileReader.read();
            }
            SelectionSort(array);

            String newString = "";
            for(int a : array){
                newString += String.valueOf((char)a);
            }

            if(methodOfSort == "ascending") {
                fileWriter.write(newString);
                fileWriter.flush();
                fileWriter.close();
            }else if(methodOfSort == "descending"){
                StringBuffer stringBuffer = new StringBuffer(newString);
                stringBuffer.reverse();
                fileWriter.write(stringBuffer.toString());
                fileWriter.flush();
                fileWriter.close();
            }

        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }

    public static void SelectionSort(int arr[]) {
        for (int min = 0; min < arr.length - 1; min++) {
            int least = min;
            for (int j = min + 1; j < arr.length; j++) {
                if (arr[j] < arr[least]) {
                    least = j;
                }
            }
            int tmp = arr[min];
            arr[min] = arr[least];
            arr[least] = tmp;
        }
    }
}
