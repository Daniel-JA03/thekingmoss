package com.thekingmoss.domain.entity;

import com.thekingmoss.domain.entity.types.TipoDocumentoIdentidad;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "documento_identidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentoIdentidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "documento_identidad_id")
    private Long documentoIdentidadId;

    @Column(name = "numero_documento_identidad", nullable = false)
    private String numeroDocumentoIdentidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento_identidad", nullable = false)
    private TipoDocumentoIdentidad tipoDocumentoIdentidad;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

}
