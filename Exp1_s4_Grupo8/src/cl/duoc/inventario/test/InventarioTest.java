package cl.duoc.inventario.test;

import cl.duoc.inventario.modelo.Inventario;
import cl.duoc.inventario.modelo.Producto;

public class InventarioTest {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();

        // Prueba 1: Agregar producto válido
        Producto p1 = new Producto("001", "Polera básica", "Polera algodón M", 7990, 10);
        inventario.agregarProducto(p1);
        System.out.println("✅ Producto agregado al inventario: " + inventario.buscarPorCodigo("001"));

        // Prueba 2: Eliminar producto existente
        boolean eliminado = inventario.eliminarProducto("001");
        System.out.println(eliminado ? "✅ Producto eliminado correctamente." : "❌ ERROR al eliminar producto.");

        // Prueba 3: Buscar producto inexistente
        Producto p2 = inventario.buscarPorCodigo("999");
        System.out.println(p2 == null ? "✅ Correcto: producto inexistente no encontrado." : "❌ ERROR: se encontró un producto inexistente.");

        // Prueba 4: Inventario vacío debe retornar lista vacía
        System.out.println(inventario.listarProductos().isEmpty() ? "✅ Inventario vacío correctamente." : "❌ ERROR: inventario debería estar vacío.");
    }
}
