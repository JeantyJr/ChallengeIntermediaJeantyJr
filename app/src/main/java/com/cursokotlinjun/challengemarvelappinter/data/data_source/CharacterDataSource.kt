package com.cursokotlinjun.challengemarvelappinter.data.data_source

import com.cursokotlinjun.challengemarvelappinter.data.service.MarvelAPI
import javax.inject.Inject

class CharacterDataSource @Inject constructor(
    private val marvelAPI: MarvelAPI
): MarvelDataSource() {
    suspend fun getAllCharacters(auth: HashMap<String, String>, offset: Int, limit: Int) =
        getResult { marvelAPI.getAllCharacters(auth, offset, limit)}

    suspend fun getAllComics(
        characterId: Int, auth: HashMap<String, String>, limit: Int) =
        getResult { marvelAPI.getAllComics(characterId, auth, limit) }
}