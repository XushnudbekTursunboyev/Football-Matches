package uz.orign.footballapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import uz.orign.footballapp.network.model.standing_league.MyStandings
import uz.orign.footballapp.network.model.standing_team.MyStandingTeam
import uz.orign.footballapp.utils.CONSTANTS.API_KEY

interface ApiService {


    @Headers("X-RapidAPI-Key:$API_KEY")
    @GET("standings?season=2020&league=39")
    suspend fun getStanding(): Response<MyStandings>


    @Headers("X-RapidAPI-Key:$API_KEY")
    @GET("standings?season=2020&team=33")
    suspend fun getStandingTeam(): Response<MyStandingTeam>


}