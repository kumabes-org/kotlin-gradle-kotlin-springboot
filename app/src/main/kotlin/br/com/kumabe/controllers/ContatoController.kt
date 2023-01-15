package br.com.kumabe.controllers

import br.com.kumabe.dtos.ContatoDTO
import br.com.kumabe.mappers.ContatoMapper
import br.com.kumabe.models.Contato
import br.com.kumabe.repositories.ContatoRepository
import org.apache.logging.log4j.LogManager
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*
import java.util.stream.Collectors
import javax.validation.Valid
import kotlin.collections.ArrayList


@RestController
@RequestMapping("/api")
class ContatoController(
    private val contatoRepository: ContatoRepository,
    private val contatoMapper: ContatoMapper
    ) {
    companion object {
        private val logger = LogManager.getLogger()
    }

    @PostMapping("/v1/contatos")
    fun crate(@RequestBody @Valid contatoDTO: ContatoDTO,uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<Void> {
        val contato = contatoMapper.dto2Entity(contatoDTO)
        contatoRepository.saveAndFlush(contato)
        val location = uriComponentsBuilder.path("/api/v1/contatos/{codigo}").buildAndExpand(contato.codigo).toUri();
        return ResponseEntity.created(location).build()
    }

    @GetMapping("/v1/contatos/{codigo}")
    fun retrieve(@PathVariable("codigo") codigo:Long):ResponseEntity<ContatoDTO>{
        val optional: Optional<Contato> = contatoRepository.findById(codigo)
        if(optional.isPresent) {
            val contato = optional.get()
            val contatoDTO = contatoMapper.entity2DTO(contato)
            return ResponseEntity.ok(contatoDTO);
        }else{
            return ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/v1/contatos/{codigo}")
    fun update(@RequestBody @Valid contatoDTO: ContatoDTO, @PathVariable("codigo") codigo:Long): ResponseEntity<Void> {
        val optional: Optional<Contato> = contatoRepository.findById(codigo)
        if(!optional.isPresent) {
            return ResponseEntity.notFound().build()
        }
        val contato = contatoMapper.dto2Entity(contatoDTO)
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
    fun list(): ResponseEntity<List<ContatoDTO>> {
        val dtos = contatoRepository.findAll().stream().map {
            c -> contatoMapper.entity2DTO(c)
        }.collect(Collectors.toList())
        return ResponseEntity.ok(dtos);
    }
}
