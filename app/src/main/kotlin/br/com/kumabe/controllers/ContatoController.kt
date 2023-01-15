package br.com.kumabe.controllers

import br.com.kumabe.models.Contato
import br.com.kumabe.repositories.ContatoRepository
import javax.validation.Valid

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

import java.util.Optional


@RestController
@RequestMapping("/api")
class ContatoController(private val contatoRepository: ContatoRepository) {

    @PostMapping("/v1/contatos")
    fun crate(@RequestBody @Valid contato: Contato,uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<Void> {
        contatoRepository.saveAndFlush(contato)
        val location = uriComponentsBuilder.path("/api/v1/contatos/{codigo}").buildAndExpand(contato.codigo).toUri();
        return ResponseEntity.created(location).build()
    }

    @GetMapping("/v1/contatos/{codigo}")
    fun retrieve(@PathVariable("codigo") codigo:Long):ResponseEntity<Contato>{
        val optional: Optional<Contato> = contatoRepository.findById(codigo)
        if(optional.isPresent) {
            val contato = optional.get()
            return ResponseEntity.ok(contato);
        }else{
            return ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/v1/contatos/{codigo}")
    fun update(@RequestBody @Valid contato: Contato, @PathVariable("codigo") codigo:Long): ResponseEntity<Void> {
        val optional: Optional<Contato> = contatoRepository.findById(codigo)
        if(!optional.isPresent) {
            return ResponseEntity.notFound().build()
        }
        contatoRepository.saveAndFlush(contato)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/v1/contatos/{codigo}")
    fun delete(@PathVariable("codigo") codigo:Long): ResponseEntity<Void> {
        val optional: Optional<Contato> = contatoRepository.findById(codigo)
        if(!optional.isPresent) {
            return ResponseEntity.notFound().build()
        }
        val contato = optional.get()
        contatoRepository.delete(contato)
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/v1/contatos")
    fun list():ResponseEntity<List<Contato>>{
        val contatos = contatoRepository.findAll()
        return ResponseEntity.ok(contatos);
    }

}
