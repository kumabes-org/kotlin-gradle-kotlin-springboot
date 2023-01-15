package br.com.kumabe.controllers

import br.com.kumabe.models.Contato

import br.com.kumabe.repositories.ContatoRepository
import io.mockk.mockk
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ContatoControllerTests(
    private val contatoRepository: ContatoRepository = mockk()
) {

    fun getContatos() : List<Contato> {
        var contatos: List<Contato> = ArrayList()
        contatos.plus(Contato(1L, "1", "1", LocalDate.of(1995,1,1)))
        contatos.plus(Contato(2L, "2", "2", LocalDate.of(1995,2,2)))
        contatos.plus(Contato(3L, "3", "3", LocalDate.of(1995,3,3)))
        return contatos
    }
}