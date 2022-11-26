package com.cursokotlinjun.challengemarvelappinter.domain.repository

import com.cursokotlinjun.challengemarvelappinter.data.data_source.Ressource
import com.cursokotlinjun.challengemarvelappinter.data.data_source.dto.Data
import com.cursokotlinjun.challengemarvelappinter.domain.model.Character
import com.cursokotlinjun.challengemarvelappinter.domain.model.Comic

interface CharactersRepository {
    suspend fun getAllCharacters(
        offset: Int, limit: Int = 15
    ):Ressource<List<Character>>

    suspend fun getAllComics(
        characterId:Int, limit: Int = 15
    ):Ressource<List<Comic>>
}