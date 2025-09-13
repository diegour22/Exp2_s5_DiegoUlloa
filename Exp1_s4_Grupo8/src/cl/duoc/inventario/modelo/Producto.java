package cl.duoc.inventario.modelo;

/**
 * Representa un producto en el inventario.
 */
public class Producto {

    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

    // Constructor con validaciones
    public Producto(String codigo, String nombre, String descripcion, double precio, int stock) {
        if (codigo == null || codigo.isBlank()) throw new IllegalArgumentException("⚠️ Código no puede estar vacío");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("⚠️ Nombre no puede estar vacío");
        if (precio < 0) throw new IllegalArgumentException("⚠️ El precio no puede ser negativo");
        if (stock < 0) throw new IllegalArgumentException("⚠️ El stock no puede ser negativo");

        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y Setters con validaciones
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("⚠️ Nombre no puede estar vacío");
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0) throw new IllegalArgumentException("⚠️ Precio inválido, debe ser positivo");
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("⚠️ Stock inválido, debe ser mayor o igual a 0");
        this.stock = stock;
    }

    // Métodos adicionales de actualización
    public void actualizarPrecio(double nuevoPrecio) {
        setPrecio(nuevoPrecio);
    }

    public void actualizarNombre(String nuevoNombre) {
        setNombre(nuevoNombre);
    }

    public void actualizarStock(int nuevoStock) {
        setStock(nuevoStock);
    }

    // Métodos de negocio
    public void aumentarStock(int cantidad) {
        if (cantidad > 0) this.stock += cantidad;
        else throw new IllegalArgumentException("⚠️ La cantidad a aumentar debe ser mayor a 0");
    }

    public void disminuirStock(int cantidad) {
        if (cantidad <= 0) throw new IllegalArgumentException("⚠️ La cantidad a disminuir debe ser mayor a 0");
        if (cantidad > this.stock) throw new IllegalArgumentException("⚠️ No hay suficiente stock disponible");
        this.stock -= cantidad;
    }

    @Override
    public String toString() {
        return "Código: " + codigo +
                " | Nombre: " + nombre +
                " | Descripción: " + descripcion +
                " | Precio: $" + precio +
                " | Stock: " + stock;
    }
}
