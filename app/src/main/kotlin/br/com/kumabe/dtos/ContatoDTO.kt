package br.com.kumabe.dtos

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@JsonIgnoreProperties(ignoreUnknown = true)
data class ContatoDTO(
    val codigo: Long,
    @NotEmpty
    val nome: String,
    @NotEmpty
    val telefone: String,
    @NotNull
    @JsonProperty("data_nascimento")
    val dataNascimento: LocalDate
)