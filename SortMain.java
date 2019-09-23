package main;
import java.io.*;

public class SortMain {

    public static void main(String[] args)throws Exception {
        System.out.println("Sort 2019 Maksim Efanov 8311: 2019-09-06\n");
        int count = args.length;

        if (count == 0)
            System.out.println("Использование: Sort.exe /?\n");
        else if (args[0].equals("/?")) {
            System.out.println("Использование: Sort.exe ВходнойФайл [ВыходнойФайл] [/<сортировка>] [/<тип сортировки>]\n");
            System.out.println("<сортировка>\n  ascending - по умолчанию\n  descending\n");
            System.out.println("<тип сортировки>\n  comb - по умолчанию\n  selection\n");
            //Обработка ошибок пользователя
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
            inputFileName = args[0];
            //Получение имени выходного файла
            if (count >= 2 && !args[1].contains("/")) {
                //Проверка имени выходного файла на допустимость
                    try {
                        Choice(args[1]);
                        FileWriter fileWriter = new FileWriter(args[1]);
                        fileWriter.flush();
                        fileWriter.close();
                    } catch (IOException e) {
                        System.out.println("Недопустимый символ в имени выходного файла");
                        System.exit(0);
                    }
                outputFileName = args[1];
            } else {
                outputFileName = inputFileName.replace(".txt", ".sort");
                Choice(outputFileName);
            }


            //Значения по умолчанию
            methodOfSort = "ascending";
            typeOfSort = "comb";

            for (int i = 1; i < count; i++) {
                if (args[i].equals("/ascending")) {
                    methodOfSort = "ascending";
                } else if (args[i].equals("/descending")) {
                    methodOfSort = "descending";
                } else if (args[i].equals("/comb")) {
                    typeOfSort = "comb";
                } else if (args[i].equals("/selection")) {
                    typeOfSort = "selection";
                } else if (args[i].startsWith("/")) {
                    System.out.println("Неверные параметры\n\nИспользование: Sort.exe /?");
                    System.exit(0);
                }
            }
            Sorting(inputFileName, outputFileName, methodOfSort, typeOfSort);
        }
    }

    public static void Sorting(String inputFileName, String outputFileName, String methodOfSort, String typeOfSort) {
            //File fileOut = new File(outputFileName);
        try {
            File file = new File(inputFileName);
            //Класс FileReader позволяет считывать данные из файла
            FileReader fileReader = new FileReader(inputFileName);
            //file.length() возвращает длину файла(количество символов, входящих в него)
            int lengthOfFile = (int) file.length();
            int arr[] = new int[lengthOfFile];

            for (int i = 0; i <= lengthOfFile - 1; i++) {
                arr[i] = fileReader.read();
            }
            fileReader.close();

            if (typeOfSort == "selection")
                SelectionSort(arr);
            else
                CombSort(arr);
            //Создание строки и помещение в неё отсортированного массива элементов
            String sortMasString = "";
            for (int a : arr) {
                sortMasString += String.valueOf((char) a);
            }
            //System.out.println("Выходной файл " + outputFileName);
            AscOrDesc(methodOfSort, outputFileName, sortMasString);

        } catch (IOException e) {
            System.out.println("Файл не найден");
        }catch (OutOfMemoryError e){
            System.out.println("Недостаточно памяти");
        }catch (SecurityException e){
            System.out.println("Файл защищён");
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


    public static void CombSort(int arr[]) {
        int gap = arr.length;
        float reductionFactor = 1.247f;
        boolean swapped = false;

        while (gap > 1 || swapped) {
            if (gap > 1) {
                gap = (int) (gap / reductionFactor);
            }
            swapped = false;

            for (int i = 0; gap + i < arr.length; i++) {
                if (arr[i] > arr[i + gap]) {
                    Integer temp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = temp;
                    swapped = true;
                }
            }
        }
    }

    //Метод, позволяющий выбрать метод сортировки
    public static void AscOrDesc(String methodOfSort, String outputFileName, String sortMasString) throws IOException {
        FileWriter fileWriter = new FileWriter(outputFileName);
        if (methodOfSort == "ascending") {
            fileWriter.write(sortMasString);
            fileWriter.flush();
            fileWriter.close();
        } else if (methodOfSort == "descending") {
            //В классе StringBuffer присутствует метод reverse(), переворачивающий строку
            StringBuffer stringBuffer = new StringBuffer(sortMasString);
            stringBuffer.reverse();
            fileWriter.write(stringBuffer.toString());
            fileWriter.flush();
            fileWriter.close();
        }
    }

    public static void Choice(String outputFileName) throws IOException {
        File file = new File(outputFileName);

        if (file.exists() && !file.isDirectory()) {
            System.out.println("Выходной файл " + outputFileName);
            System.out.println("Перезаписать файл? y/n");
            char choice = (char) System.in.read();
            if (choice == 'y' || choice == 'Y');

            else {
                System.out.println("Выходной файл не был изменён");
                System.exit(0);
            }

            }
        }
    }



