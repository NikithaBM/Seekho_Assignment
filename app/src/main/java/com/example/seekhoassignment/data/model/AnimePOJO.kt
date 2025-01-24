package com.example.seekhoassignment.data.model


data class AnimePOJO(
    val data: List<TopAnime>,
    val pagination: Pagination
)

data class Pagination(
    val last_visible_page: Int,
    val has_next_page: Boolean,
    val current_page: Int,
    val items: Items
)

data class Items(
    val count: Int,
    val total: Int,
    val per_page: Int
)

data class TopAnime(
    val mal_id: Int,
    val rank: Int,
    val title: String,
    val images: Images,
    val score: Double,
    val episodes: Int,
    val duration: String
)

data class Images(
    val jpg: Image
)

data class Image(
    val image_url: String
)


/*
data class AnimePOJO(
    val mal_id: Int,
    val title: String,
    val episodes: Int,
    val score: Double,
    val images: Images
)

data class Images(
    val jpg: Jpg
)

data class Jpg(
    val image_url: String
)

data class AnimeDetail(
    val mal_id: Int,
    val title: String,
    val synopsis: String,
    val genres: List<Genre>,
    val episodes: Int,
    val score: Double,
    val trailer: Trailer?,
    val images: Images
)

data class Genre(
    val name: String
)

data class Trailer(
    val url: String?
)
*/
