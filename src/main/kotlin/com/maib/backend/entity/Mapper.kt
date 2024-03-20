package com.maib.backend.entity

interface Mapper<ENTITY, DTO> {
    fun entityFromDto(dto: DTO):ENTITY
    fun dtoFromEntity(entity: ENTITY): DTO

}