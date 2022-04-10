package hackthon.symptompredictor.network

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*
import retrofit2.http.Body

import retrofit2.http.POST
import java.util.function.BinaryOperator

private const val BASE_URL = "https://priaid-symptom-checker-v1.p.rapidapi.com/"

// The API Interface
interface ApiMedicApiService{
    // gets a full list of symptoms
    @GET("symptoms")
    fun getAllSymptoms(@Query("language")language:String, @Query("rapidapi-key")apiKey: String):Call<List<Symptoms>>

    // gets a list of symptoms matching the search
    @GET("symptoms")
    fun getSymptoms(@Query("symptoms")symptoms: String, @Query("language")language:String, @Query("rapidapi-key")apiKey: String):Call<List<Symptoms>>

    // gets a list of symptoms that are likely to happen at the same time given a list of symptoms
    @GET("symptoms/proposed")
    fun getProposedSymptoms(@Query("gender")gender:String, @Query("year_of_birth")yearOfBirth: Int, @Query("symptoms")symptoms: String,
                            @Query("language")language:String, @Query("rapidapi-key")apiKey: String):Call<List<Symptoms>>

    // gets a diagonosis based on gender, year of birth, and symptoms
    @GET("diagnosis")
    fun getDiagnosis(@Query("gender")gender:String, @Query("year_of_birth")yearOfBirth: Int, @Query("symptoms")symptoms: String,
                     @Query("language")language:String, @Query("rapidapi-key")apiKey: String):Call<List<Diagnosis>>

    // gets detailed information about an issue
    @GET("issues/{issue_id}/info")
    fun getIssueInfo(@Path("issue_id")issueId:Int, @Query("language")language:String, @Query("rapidapi-key")apiKey: String):Call<IssueInfo>
}

//initialize Moshi
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//initialize retrofit
private val client = OkHttpClient.Builder().build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

object ApiMedicApi {
    val retrofitService: ApiMedicApiService by lazy{
        retrofit.create(ApiMedicApiService::class.java)
    }
}

data class Symptoms(
    @Json(name = "ID")
    val id: Int,

    @Json(name = "Name")
    var name: String
)

data class Diagnosis(
    @Json(name = "Issue")
    val issue: Issue,

    @Json(name = "Specialisation")
    var specialisation: List<Specialisation>?
)

data class Specialisation(
    @Json(name = "ID")
    val id: Int,

    @Json(name = "Name")
    var name: String,

    @Json(name = "SpecialistID")
    val specialistId: Int
)

data class Issue(
    @Json(name = "ID")
    val id: Int,

    @Json(name = "Name")
    var name: String,

    @Json(name = "ProfName")
    val profName: String,

    @Json(name = "Ranking")
    val ranking: Int
)

data class IssueInfo(
    @Json(name = "Name")
    val name: String,

    @Json(name = "ProfName")
    val profName: String,

    @Json(name = "Synonyms")
    val synonyms: String,

    @Json(name = "Description")
    val description: String,

    @Json(name = "MedicalCondition")
    var medicalCondition: String,

    @Json(name = "PossibleSymptoms")
    val possibleSymptoms: String,

    @Json(name = "TreatmentDescription")
    val treatmentDescription: String
)