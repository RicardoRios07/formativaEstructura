/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.gestortareas;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author riosr
 */
public class GestorTareas {

    class Tarea {

        private String nombre;
        private String descripcion;
        private String diaEntrega;
        private int importancia;
        private Date fechaAgregada;

        public Tarea(String nombre, String descripcion, String diaEntrega, int importancia, Date fechaAgregada) {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.diaEntrega = diaEntrega;
            this.importancia = importancia;
            this.fechaAgregada = fechaAgregada;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDiaEntrega() {
            return diaEntrega;
        }

        public void setDiaEntrega(String diaEntrega) {
            this.diaEntrega = diaEntrega;
        }

        public int getImportancia() {
            return importancia;
        }

        public void setImportancia(int importancia) {
            this.importancia = importancia;
        }

        public Date getFechaAgregada() {
            return fechaAgregada;
        }

        public void setFechaAgregada(Date fechaAgregada) {
            this.fechaAgregada = fechaAgregada;
        }

    }

  private Stack<Tarea> pilaTareas;
    private Queue<Tarea> colaTareas;
    private LinkedList<Tarea> listaTareas;

    public GestorTareas() {
        pilaTareas = new Stack<>();
        colaTareas = new LinkedList<>();
        listaTareas = new LinkedList<>();
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("===============================================");
            System.out.println("========= GESTOR DE TAREAS PENDIENTES =========");
            System.out.println("===============================================");
            System.out.println("Seleccione una opción del gestor de tareas:");
            System.out.println("1. Agregar Tarea");
            System.out.println("2. Eliminar Tarea");
            System.out.println("3. Listar tareas pendientes");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarTarea();
                    break;
                case 2:
                    eliminarTarea();
                    break;
                case 3:
                    listarTareas();
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }

        } while (opcion != 4);
    }

    public void agregarTarea() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("== Agregar Tarea ==");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Día de entrega: ");
        String diaEntrega = scanner.nextLine();
        System.out.print("Importancia (1-5): ");
        int importancia = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de leer el importancia

        Date fechaAgregada = new Date();

        Tarea tarea = new Tarea(nombre, descripcion, diaEntrega, importancia, fechaAgregada);
        pilaTareas.push(tarea);
        colaTareas.add(tarea);
        listaTareas.add(tarea);

        System.out.println("Tarea agregada con éxito.");
    }

    public void eliminarTarea() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("== Eliminar Tarea ==");
        System.out.print("Ingrese el nombre de la tarea a eliminar: ");
        String nombre = scanner.nextLine();

        boolean tareaEncontrada = false;

        // Eliminar de la pila
        Stack<Tarea> pilaAuxiliar = new Stack<>();
        while (!pilaTareas.isEmpty()) {
            Tarea tarea = pilaTareas.pop();
            if (tarea.getNombre().equals(nombre)) {
                tareaEncontrada = true;
                break;
            }
            pilaAuxiliar.push(tarea);
        }
        while (!pilaAuxiliar.isEmpty()) {
            pilaTareas.push(pilaAuxiliar.pop());
        }

        // Eliminar de la cola
        colaTareas.removeIf(t -> t.getNombre().equals(nombre));

        // Eliminar de la lista
        listaTareas.removeIf(t -> t.getNombre().equals(nombre));

        if (tareaEncontrada) {
            System.out.println("Tarea eliminada con éxito.");
        } else {
            System.out.println("No se encontró una tarea con ese nombre.");
        }
    }

    public void listarTareas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("== Listar Tareas ==");
        System.out.println("Seleccione el orden de la lista:");
        System.out.println("1. De más reciente a más antiguo (Pila)");
        System.out.println("2. De más antiguo a más reciente (Cola)");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("== Tareas Pendientes (Pila) ==");
                if (pilaTareas.isEmpty()) {
                    System.out.println("No hay tareas pendientes.");
                } else {
                    for (Tarea tarea : pilaTareas) {
                        System.out.println("Nombre: " + tarea.getNombre());
                        System.out.println("Descripción: " + tarea.getDescripcion());
                        System.out.println("Día de entrega: " + tarea.getDiaEntrega());
                        System.out.println("Importancia: " + tarea.getImportancia());
                        System.out.println("Fecha de agregada: " + tarea.getFechaAgregada());
                        System.out.println("-------------------------------------");
                    }
                }
                break;
            case 2:
                System.out.println("== Tareas Pendientes (Cola) ==");
                if (colaTareas.isEmpty()) {
                    System.out.println("No hay tareas pendientes.");
                } else {
                    for (Tarea tarea : colaTareas) {
                        System.out.println("Nombre: " + tarea.getNombre());
                        System.out.println("Descripción: " + tarea.getDescripcion());
                        System.out.println("Día de entrega: " + tarea.getDiaEntrega());
                        System.out.println("Importancia: " + tarea.getImportancia());
                        System.out.println("Fecha de agregada: " + tarea.getFechaAgregada());
                        System.out.println("-------------------------------------");
                    }
                }
                break;
            default:
                System.out.println("Opción inválida.");
                break;
        }
    }

    public static void main(String[] args) {
        GestorTareas gestorTareas = new GestorTareas();
        gestorTareas.menu();
    }
}