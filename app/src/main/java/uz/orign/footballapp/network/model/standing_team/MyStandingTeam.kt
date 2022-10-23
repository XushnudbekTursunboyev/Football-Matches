package uz.orign.footballapp.network.model.standing_team

data class MyStandingTeam(
    val errors: List<Any>,
    val `get`: String,
    val paging: Paging,
    val parameters: Parameters,
    val response: List<Response>,
    val results: Int
)