package main;
import java.io.FileReader;
import java.io.IOException;

public class SortMain {

    public static void main(String[] args) {
        System.out.println("Sort 2019 Maksim Efanov 8311: 2019-09-06\n");
        int count = args.length;

        if (count == 0)
            System.out.println("Usage: ИмяПрограммы /?\n");
        else if (args[0].equals("/?")) {
            System.out.println("Usage: ИмяПрограммы ВходнойФайл [ВыходнойФайл] [/<сортировка>] [/<тип сортировки>]\n");
            System.out.println("<сортировка>\n  ascending - по умолчанию\n  descending\n");
            System.out.println("<тип сортировки>\n  tree - по умолчанию\n  selection\n");
        }else if(args[0].startsWith("/")){
            System.out.println("Вы не указали входной файл");
        }else if(count == 4 && (!args[2].startsWith("/") || !args[3].startsWith("/"))){
            System.out.println("Неверные параметры\n\nUsage: ИмяПрограммы /?");
        }else if(count == 3 && !args[2].startsWith("/")){
            System.out.println("Неверные параметры\n\nUsage: ИмяПрограммы /?");
        }else if(count > 4){
            System.out.println("Слишком много аргументов");
        } else{
           String inputFileName, outputFileName, methodOfSort, typeOfSort;

                inputFileName = args[0];
                outputFileName = inputFileName + ".sort";
                methodOfSort = "ascending";
                typeOfSort = "tree";

            for (int i = 1; i < count; i++) {
                if(args[i].equals("/ascending")) {
                    methodOfSort = "ascending";
                    outputFileName = inputFileName + ".sort";
                }
                else if(args[i].equals("/descending")) {
                    methodOfSort = "descending";
                    outputFileName = inputFileName + ".sort";
                }
                else if(args[i].equals("/tree")) {
                    typeOfSort = "tree";
                    outputFileName = inputFileName + ".sort";
                    //methodOfSort = "ascending";
                }
                else if(args[i].equals("/selection")) {
                    typeOfSort = "selection";
                    outputFileName = inputFileName + ".sort";
                    //methodOfSort = "ascending";
                }
                else if(args[i].startsWith("/")) {
                    System.out.println("Выходной файл не может начинаться со знака /");
                    System.exit(0);
                }
                else outputFileName = args[i];
            }

            System.out.println(inputFileName);
            System.out.println(outputFileName);
            System.out.println(methodOfSort);
            System.out.println(typeOfSort);
           //Sorting(inputFileName, );

       }
    }
    public static void Sorting(String inputFileName, String outputFileName, String methodOfSort, String typeOfSort){
        try {
            FileReader fileReader = new FileReader(inputFileName);
            int c;
            while((c = fileReader.read()) != -1){

                System.out.print((char)c);
            }
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }
}
