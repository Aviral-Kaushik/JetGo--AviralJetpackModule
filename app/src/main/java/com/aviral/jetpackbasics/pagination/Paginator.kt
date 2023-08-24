package com.aviral.jetpackbasics.pagination

interface Paginator<Key, Item> {

    suspend fun loadNextItem()

    fun reset()

}