package com.example.seekhoassignment.data.model
/*

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
)*/

/*data class AnimeDetail(
    val mal_id: Int,
    val url: String,
    val images: Images,
    val trailer: Trailer,
    val title: String,
    val type: String,
    val episodes: Int?,
    val status: String,
    val aired: Aired,
    val synopsis: String,
    val background: String?,
    val rating: String?,
    val score: Double?,
    val genres: List<Genre>,
    val themes: List<Theme>,
    val demographics: List<Demographic>
) {
    data class Images(
        val jpg: ImageVariant,
        val webp: ImageVariant
    )

    data class ImageVariant(
        val image_url: String,
        val small_image_url: String,
        val large_image_url: String
    )

    data class Trailer(
        val youtube_id: String?,
        val url: String?,
        val embed_url: String?
    )

    data class Aired(
        val from: String?,
        val to: String?
    )

    data class Genre(
        val mal_id: Int,
        val type: String,
        val name: String
    )

    data class Theme(
        val mal_id: Int,
        val type: String,
        val name: String
    )

    data class Demographic(
        val mal_id: Int,
        val type: String,
        val name: String
    )
}*/

data class AnimeDetail(
    val data: AnimeData
)

data class AnimeData(
    val mal_id: Int,
    val title: String,
    val title_english: String?,
    val title_japanese: String,
    val synopsis: String,
    val type: String,
    val episodes: Int?,
    val score: Double?,
    val rating: String,
    val images: Images,
    val image_url: String,
    val trailer: Trailer?,
    val genres: List<Genre>,
    val staff: List<StaffMember>,
    val cast: List<CastMember>
)

data class Trailer(
    val youtube_id: String?,
    val url: String?
)

data class Genre(
    val mal_id: Int,
    val name: String,
    val type: String
)

data class StaffMember(
    val mal_id: Int,
    val name: String,
    val position: String
)

data class CastMember(
    val mal_id: Int,
    val name: String,
    val role: String
)
