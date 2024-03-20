package com.maib.backend

import com.maib.backend.entity.Mapper

interface PersonalizedMapper<ENTITY,DTO>: Mapper<ENTITY, DTO> {
    fun dtoFromEntityPersonalized(entity: ENTITY, userId:String):DTO
}