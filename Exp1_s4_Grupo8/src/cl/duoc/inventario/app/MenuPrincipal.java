package cl.duoc.inventario.app;

import cl.duoc.inventario.modelo.Inventario;
import cl.duoc.inventario.modelo.Producto;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal con menú de interacción con el usuario
 */
public class MenuPrincipal {

    private static Inventario inventario = new Inventario();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = -1;

        do {
            mostrarMenu();
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer

                switch (opcion) {
                    case 1 -> agregarProducto();
                    case 2 -> eliminarProducto();
                    case 3 -> actualizarProducto();
                    case 4 -> buscarProductoPorCodigo();
                    case 5 -> listarProductos();
                    case 6 -> generarReporte();
                    case 0 -> System.out.println("👋 Saliendo del sistema...");
                    default -> System.out.println("⚠️ Opción inválida. Intente nuevamente.");
                }

            } catch (InputMismatchException e) {
                System.out.println("⚠️ Error: Debe ingresar un número válido.");
                scanner.nextLine(); // limpiar entrada incorrecta
            } catch (Exception e) {
                System.out.println("⚠️ Error: " + e.getMessage());
            }

        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n===== 📋 Menú Principal =====");
        System.out.println("1️⃣ Agregar producto");
        System.out.println("2️⃣ Eliminar producto");
        System.out.println("3️⃣ Actualizar producto");
        System.out.println("4️⃣ Buscar producto por código");
        System.out.println("5️⃣ Listar todos los productos");
        System.out.println("6️⃣ Generar reporte estadístico");
        System.out.println("0️⃣ Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void agregarProducto() {
        try {
            System.out.print("Ingrese código: ");
            String codigo = scanner.nextLine();
            System.out.print("Ingrese nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese descripción: ");
            String descripcion = scanner.nextLine();
            System.out.print("Ingrese precio: ");
            double precio = scanner.nextDouble();
            System.out.print("Ingrese stock: ");
            int stock = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            Producto producto = new Producto(codigo, nombre, descripcion, precio, stock);
            inventario.agregarProducto(producto);

            System.out.println("✅ Producto agregado correctamente.");
        } catch (Exception e) {
            System.out.println("⚠️ Error al agregar producto: " + e.getMessage());
            scanner.nextLine(); // limpiar si quedó algo mal
        }
    }

    private static void eliminarProducto() {
        System.out.print("Ingrese código del producto a eliminar: ");
        String codigo = scanner.nextLine();

        if (inventario.eliminarProducto(codigo)) {
            System.out.println("🗑️ Producto eliminado correctamente.");
        } else {
            System.out.println("⚠️ No se encontró un producto con ese código.");
        }
    }

    private static void actualizarProducto() {
        System.out.print("Ingrese código del producto a actualizar: ");
        String codigo = scanner.nextLine();

        System.out.print("Nuevo nombre (dejar vacío para no cambiar): ");
        String nuevoNombre = scanner.nextLine();

        System.out.print("Nuevo precio (ingrese -1 para no cambiar): ");
        double nuevoPrecio = scanner.nextDouble();

        System.out.print("Nuevo stock (ingrese -1 para no cambiar): ");
        int nuevoStock = scanner.nextInt();
        scanner.nextLine();

        boolean actualizado = inventario.actualizarProducto(
                codigo,
                nuevoNombre.isBlank() ? null : nuevoNombre,
                nuevoPrecio >= 0 ? nuevoPrecio : -1,
                nuevoStock >= 0 ? nuevoStock : -1
        );

        if (actualizado) {
            System.out.println("✅ Producto actualizado correctamente.");
        } else {
            System.out.println("⚠️ No se encontró un producto con ese código.");
        }
    }

    private static void buscarProductoPorCodigo() {
        System.out.print("Ingrese código del producto: ");
        String codigo = scanner.nextLine();

        Producto producto = inventario.buscarPorCodigo(codigo);
        if (producto != null) {
            System.out.println("🔍 Producto encontrado: " + producto);
        } else {
            System.out.println("⚠️ No se encontró un producto con ese código.");
        }
    }

    private static void listarProductos() {
        List<Producto> lista = inventario.listarProductos();
        if (lista.isEmpty()) {
            System.out.println("📦 No hay productos en el inventario.");
        } else {
            System.out.println("===== 📦 Lista de productos =====");
            for (Producto p : lista) {
                System.out.println(p);
            }
        }
    }

    private static void generarReporte() {
        inventario.generarReporteEstadistico();
    }
}
