package com.blakeshop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blakeshop.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	
	Page<Producto> findBySeccion(Pageable page, String seccion);
	
	@Query( value = "SELECT * FROM Producto p INNER JOIN Marca m ON p.id_marca = m.id_marca WHERE m.nombre=:marca", nativeQuery = true)
	Page<Producto> filtroMarca(Pageable page, @Param("marca") String marca);
	
	@Query( value = "SELECT * FROM Producto p INNER JOIN Categoria c ON p.id_categoria = c.id_categoria WHERE c.nombre=:categoria", nativeQuery = true)
	Page<Producto> filtroCategoria(Pageable page, @Param("categoria") String categoria);
	
	@Query( value = "SELECT * FROM Producto p INNER JOIN Marca m ON p.id_marca = m.id_marca WHERE (p.seccion=:seccion) AND (m.nombre=:marca)", nativeQuery = true)
	Page<Producto> filtroMarcaSeccion(Pageable page,@Param("marca") String marca, @Param("seccion") String seccion);
	
	@Query( value = "SELECT * FROM Producto p INNER JOIN Categoria c ON p.id_categoria = c.id_categoria WHERE (p.seccion=:seccion) AND (c.nombre=:categoria) AND (c.seccion=:seccion)", nativeQuery = true)
	Page<Producto> filtroCategoriaSeccion(Pageable page,@Param("categoria") String categoria, @Param("seccion") String seccion);
	
	@Query( value = "SELECT * FROM Producto p INNER JOIN Marca m ON p.id_marca = m.id_marca INNER JOIN Categoria c ON p.id_categoria = c.id_categoria WHERE (m.nombre=:marca) AND (c.nombre=:categoria)", nativeQuery = true)
	Page<Producto> filtroCategoriaMarca(Pageable page, @Param("categoria") String categoria, @Param("marca") String marca);
	
	@Query( value = "SELECT * FROM Producto p INNER JOIN Marca m ON p.id_marca = m.id_marca INNER JOIN Categoria c ON p.id_categoria = c.id_categoria WHERE (m.nombre=:marca) AND (c.nombre=:categoria) AND (p.seccion=:seccion) AND (c.seccion=:seccion)", nativeQuery = true)
	Page<Producto> filtroCategoriaMarcaSeccion(Pageable page, @Param("categoria") String categoria, @Param("marca") String marca, @Param("seccion") String seccion);
	
	@Query( value = "SELECT * FROM Producto p WHERE (p.oferta>:condicionOferta)", nativeQuery = true)
	Page<Producto> filtroOferta(Pageable page, @Param("condicionOferta") int condicionOferta);
	
}
