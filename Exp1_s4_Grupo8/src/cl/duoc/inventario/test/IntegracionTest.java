package cl.duoc.inventario.test;

import cl.duoc.inventario.modelo.Inventario;
import cl.duoc.inventario.modelo.Producto;

public class IntegracionTest {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();

        // Crear productos de ropa
        Producto p1 = new Producto("001", "Polera básica", "Polera de algodón talla M", 7990, 10);
        Producto p2 = new Producto("002", "Jeans clásico", "Jeans azul talla 42", 15990, 3);
        Producto p3 = new Producto("003", "Chaqueta", "Chaqueta impermeable talla L", 29990, 1);

        // Agregar productos
        inventario.agregarProducto(p1);
        inventario.agregarProducto(p2);
        inventario.agregarProducto(p3);

        // Listar productos
        System.out.println("===== 📦 Listado de productos =====");
        for (Producto p : inventario.listarProductos()) {
            System.out.println(p);
        }

        // Buscar producto por código
        System.out.println("\n===== 🔍 Búsqueda de producto =====");
        System.out.println("Buscando código 001: " + inventario.buscarPorCodigo("001"));
        System.out.println("Buscando código 999 (inexistente): " + inventario.buscarPorCodigo("999"));

        // Actualizar producto
        System.out.println("\n===== 🔄 Actualización de producto =====");
        boolean actualizado = inventario.actualizarProducto("002", "Jeans Slim", 17990, 5);
        if (actualizado) {
            System.out.println("Producto actualizado: " + inventario.buscarPorCodigo("002"));
        } else {
            System.out.println("⚠️ Producto no encontrado.");
        }

        // Generar reporte
        System.out.println("\n===== 📊 Reporte estadístico =====");
        inventario.generarReporteEstadistico();
    }
}
