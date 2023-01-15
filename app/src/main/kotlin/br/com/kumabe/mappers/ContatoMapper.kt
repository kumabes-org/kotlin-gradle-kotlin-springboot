package br.com.kumabe.mappers

import br.com.kumabe.dtos.ContatoDTO
import br.com.kumabe.models.Contato
import org.springframework.stereotype.Component

@Component
class ContatoMapper:Mapper<Contato, ContatoDTO> {
    override fun entity2DTO(t: Contato): ContatoDTO {
        val dto: ContatoDTO = ContatoDTO(t.codigo, t.nome, t.telefone, t.dataNascimento)
        return dto
    }

    override fun dto2Entity(u: ContatoDTO): Contato {
        val contato: Contato = Contato(u.codigo, u.nome, u.telefone, u.dataNascimento)
        return contato
    }

}