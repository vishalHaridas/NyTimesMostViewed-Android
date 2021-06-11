package com.example.nytmostpopular.data.util

interface EntityMapper <Entity, DomainModel>{

    fun mapFromEntityList(entityList: List<Entity>) : List<DomainModel>

    fun mapFromEntity(entity: Entity) : DomainModel
}