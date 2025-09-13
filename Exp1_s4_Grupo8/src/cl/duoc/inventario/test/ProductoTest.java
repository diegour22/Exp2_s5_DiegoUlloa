package cl.duoc.inventario.test;

import cl.duoc.inventario.modelo.Producto;

public class ProductoTest {
    public static void main(String[] args) {
        // Prueba 1: Crear producto válido
        Producto p1 = new Producto("001", "Polera básica", "Polera algodón M", 7990, 10);
        System.out.println("✅ Producto creado correctamente: " + p1);

        // Prueba 2: Precio negativo debe lanzar error
        try {
            Producto p2 = new Producto("002", "Jeans", "Jeans azul 42", -1000, 5);
            System.out.println("❌ ERROR: Se permitió crear producto con precio negativo.");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Correcto: no se permite precio negativo → " + e.getMessage());
        }

        // Prueba 3: Stock negativo debe lanzar error
        try {
            Producto p3 = new Producto("003", "Chaqueta", "Chaqueta L", 29990, -2);
            System.out.println("❌ ERROR: Se permitió stock negativo.");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Correcto: no se permite stock negativo → " + e.getMessage());
        }

        // Prueba 4: Actualizar precio válido
        p1.actualizarPrecio(8990);
        System.out.println("✅ Precio actualizado correctamente: " + p1.getPrecio());

        // Prueba 5: Disminuir stock mayor al disponible
        try {
            p1.disminuirStock(50);
            System.out.println("❌ ERROR: Se permitió disminuir más stock del disponible.");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Correcto: no se permite disminuir más stock del que hay → " + e.getMessage());
        }
    }
}
