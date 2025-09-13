package cl.duoc.inventario.modelo;

import java.util.*;

/**
 * Gestiona un conjunto de productos.
 */
public class Inventario {

    private Map<String, Producto> productos;

    public Inventario() {
        this.productos = new HashMap<>();
    }

    // Agregar producto
    public void agregarProducto(Producto producto) {
        if (producto == null) throw new IllegalArgumentException("⚠️ El producto no puede ser nulo");
        productos.put(producto.getCodigo(), producto);
    }

    // Eliminar producto por código
    public boolean eliminarProducto(String codigo) {
        return productos.remove(codigo) != null;
    }

    // Buscar producto por código
    public Producto buscarPorCodigo(String codigo) {
        return productos.get(codigo);
    }

    // Buscar producto por nombre (coincidencia parcial)
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos.values()) {
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    // Listar todos los productos
    public List<Producto> listarProductos() {
        return new ArrayList<>(productos.values());
    }

    // Actualizar producto por código (nombre, precio, stock)
    public boolean actualizarProducto(String codigo, String nuevoNombre, double nuevoPrecio, int nuevoStock) {
        Producto producto = productos.get(codigo);
        if (producto != null) {
            if (nuevoNombre != null && !nuevoNombre.isBlank()) {
                producto.actualizarNombre(nuevoNombre);
            }
            if (nuevoPrecio >= 0) {
                producto.actualizarPrecio(nuevoPrecio);
            }
            if (nuevoStock >= 0) {
                producto.actualizarStock(nuevoStock);
            }
            return true;
        }
        return false;
    }

    // Generar reporte estadístico del inventario
    public void generarReporteEstadistico() {
        if (productos.isEmpty()) {
            System.out.println("⚠️ No hay productos en el inventario.");
            return;
        }

        System.out.println("===== 📊 Reporte de Inventario =====");

        // Producto más caro y más barato
        Producto masCaro = Collections.max(productos.values(), Comparator.comparingDouble(Producto::getPrecio));
        Producto masBarato = Collections.min(productos.values(), Comparator.comparingDouble(Producto::getPrecio));

        System.out.println("💰 Producto más caro: " + masCaro);
        System.out.println("💲 Producto más barato: " + masBarato);

        // Total de stock
        int totalStock = productos.values().stream().mapToInt(Producto::getStock).sum();
        System.out.println("📦 Stock total disponible: " + totalStock);

        // Productos con stock bajo (ejemplo: menos de 5)
        System.out.println("⚠️ Productos con stock bajo (< 5):");
        for (Producto p : productos.values()) {
            if (p.getStock() < 5) {
                System.out.println("   - " + p);
            }
        }
    }
}
