package uz.orign.footballapp.network.model.standing_league

data class MyStandings(
    val errors: List<Any>,
    val `get`: String,
    val paging: Paging,
    val parameters: Parameters,
    val response: List<Response>,
    val results: Int
)