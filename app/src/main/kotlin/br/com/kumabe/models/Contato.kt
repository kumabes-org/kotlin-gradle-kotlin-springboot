package br.com.kumabe.models

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "contatos")
data class Contato(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val codigo: Long,
    val nome: String,
    val telefone: String,
    @Column(name = "data_nascimento")
    val dataNascimento: LocalDate
)