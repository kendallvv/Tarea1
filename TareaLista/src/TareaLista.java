import java.io.*;
import java.util.*;

/**
 * Esta clase representa un programa de gestión de lista de estudiantes.
 * Permite al usuario ver, agregar, eliminar, editar y buscar estudiantes en una lista.
 */
public class TareaLista{

    /** Almacena la lista de estudiantes utilizando un HashSet para evitar duplicados. */
    private static HashSet<String> studentList = new HashSet<>();

    /**
     * Punto de entrada del programa.
     * Muestra un menú de opciones al usuario y ejecuta las operaciones seleccionadas.
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        
        loadList("ListaTarea.txt");

        while (option != 6) {
            System.out.println("\n---- MENU ----");
            System.out.println("1. Ver lista");
            System.out.println("2. Agregar lista");
            System.out.println("3. Remover estudiante");
            System.out.println("4. Editar estudiante");
            System.out.println("5. Buscar estudiante");
            System.out.println("6. Salir");
            System.out.print("Ingrese opcion: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    viewStudentList();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    editStudent();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("invalido.");
            }
        }
    }

    /**
     * Carga la lista de estudiantes desde un archivo de texto.
     * @param file Nombre del archivo que contiene la lista de estudiantes.
     */
    private static void loadList(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                studentList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Guarda la lista de estudiantes en un archivo de texto.
     * @param file Nombre del archivo donde se guardará la lista.
     */
    private static void saveList(String file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String student : studentList) {
                bw.write(student);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** Muestra la lista de estudiantes ordenada alfabéticamente. */
    private static void viewStudentList() {
        List<String> sortedList = new ArrayList<>(studentList);
        Collections.sort(sortedList);
        System.out.println("\n---- Lista ----");
        for (String student : sortedList) {
            System.out.println(student);
        }
    }

    /** Permite al usuario agregar un nuevo estudiante a la lista. */
    private static void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del estudiante: ");
        String newStudent = scanner.nextLine();
        studentList.add(newStudent);
        saveList("ListaTarea.txt");
        System.out.println("Estudiante agregado.");
    }

    /** Permite al usuario eliminar un estudiante de la lista. */
    private static void removeStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre a remover: ");
        String student = scanner.nextLine();
        if (studentList.remove(student)) {
            saveList("ListaTarea.txt");
            System.out.println("Estudiante removido exitosamente.");
        } else {
            System.out.println("Estudiante no existe en la lista.");
        }
    }

    /** Permite al usuario editar el nombre de un estudiante en la lista. */
    private static void editStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre a editar: ");
        String student = scanner.nextLine();
        if (studentList.contains(student)) {
            System.out.print("Ingrese el nuevo nombre: ");
            String newStudentName = scanner.nextLine();
            studentList.remove(student);
            studentList.add(newStudentName);
            saveList("ListaTarea.txt");
            System.out.println("Estudiante editado.");
        } else {
            System.out.println("Estudiante no existe en la lista.");
        }
    }

    /** Permite al usuario buscar un estudiante en la lista. */
    private static void searchStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre del estudiante a buscar: ");
        String student = scanner.nextLine();
        if (studentList.contains(student)) {
            System.out.println("El estudiante \"" + student + "\" está en la lista.");
        } else {
            System.out.println("El estudiante \"" + student + "\" no está en la lista.");
        }
    }
}

