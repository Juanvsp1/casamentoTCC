package com.tcc.casamento.entities.tema;

import com.tcc.casamento.entities.casamento.Casamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_tema")
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTema;

    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @OneToOne(mappedBy = "tema", cascade = CascadeType.ALL)
    private Casamento casamento;
}
