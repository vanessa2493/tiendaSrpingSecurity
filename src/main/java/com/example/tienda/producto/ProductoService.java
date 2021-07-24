package com.example.tienda.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository=productoRepository;
    }

    public List<Producto> listarAllProductos(){
        return productoRepository.findAll();
    }

    public Producto getProductoById(Integer producto_id) {
        Producto producto = productoRepository.findById(producto_id).orElse(null);
        return producto;
    }

    public List<Producto> getProductoByNombre(String nombre) {
        List<Producto> productos = productoRepository.findProductosByNombreContaining(nombre);
        return productos;
    }
/*
    public void save(Producto producto) {
        productoRepository.save(producto);
    }

 */
    public void save(Producto producto) {
        if (producto.getProducto_id() != null) {
            Producto productoExistente = productoRepository.findById(producto.getProducto_id()).orElse(null);
            if (productoExistente != null) {
                if (producto.getNombre() != null) productoExistente.setNombre(producto.getNombre());
                if (producto.getPrecio() != null) productoExistente.setPrecio(producto.getPrecio());
                if (producto.getStock() != null) productoExistente.setStock(producto.getStock());
                productoRepository.save(productoExistente);
            } else producto.setProducto_id(null);
        }
        productoRepository.save(producto);
    }

    public void delete(Integer producto_id) {
        productoRepository.deleteById(producto_id);
    }
}
