package com.example.tienda.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    @GetMapping("/productos")
    private List<Producto> listarTodosLosProductos(){
        return productoService.listarAllProductos();
    }

    @GetMapping("/productos/{producto_id}")
    private Producto getProductoById(@PathVariable Integer producto_id){
        return productoService.getProductoById(producto_id);
    }

    @GetMapping("/productos/buscar/{nombre}")
    private List<Producto> getProductoByNombre(@PathVariable String nombre){
        return productoService.getProductoByNombre(nombre);
    }

    @PostMapping("productos/guardar")
    private void save(@RequestBody Producto producto){
        productoService.save(producto);
    }

    @DeleteMapping("productos/{producto_id}")
    private void delete(@PathVariable Integer producto_id){
        productoService.delete(producto_id);
    }

    @GetMapping("/mi_rol")
    private String getMiRol(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().getAuthorities().toString();
    }
}
