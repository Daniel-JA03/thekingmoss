package com.thekingmoss.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    @EqualsAndHashCode.Include
    private Long productoId;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "descuento", nullable = false, precision = 5, scale = 2)
    private BigDecimal descuento;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "tamanio")
    private String tamanio;

    @Column(name = "peso", precision = 10, scale = 2)
    private BigDecimal peso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    @Builder.Default
    private List<ProductoImagen> productoImagenes = new ArrayList<>();

    @OneToMany(mappedBy = "producto")
    @Builder.Default
    private Set<DetallePedido> detallePedidos = new LinkedHashSet<>();
}
