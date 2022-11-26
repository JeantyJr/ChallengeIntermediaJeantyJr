package com.cursokotlinjun.challengemarvelappinter.domain.repository

import com.cursokotlinjun.challengemarvelappinter.data.data_source.CharacterDataSource
import com.cursokotlinjun.challengemarvelappinter.data.data_source.Ressource
import com.cursokotlinjun.challengemarvelappinter.data.data_source.dto.Data
import com.cursokotlinjun.challengemarvelappinter.domain.model.Comic
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterDataSource: CharacterDataSource
): MarvelRepository(), CharactersRepository {
    override suspend fun getAllCharacters(offset:Int, limit: Int) =
        characterDataSource.getAllCharacters(authParams.getMap(), offset, limit)

    override suspend fun getAllComics(characterId: Int, limit: Int)  =
        characterDataSource.getAllComics(characterId, authParams.getMap(),limit)

}