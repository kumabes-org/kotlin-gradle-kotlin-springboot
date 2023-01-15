package br.com.kumabe.mappers

interface Mapper<T,U> {
    fun entity2DTO(t:T):U
    fun dto2Entity(u:U):T
}