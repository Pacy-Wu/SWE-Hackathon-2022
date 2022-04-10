package hackthon.symptompredictor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hackthon.symptompredictor.network.ApiMedicApi
import hackthon.symptompredictor.network.Diagnosis
import hackthon.symptompredictor.network.Symptoms
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class SymptomDiseaseViewModel: ViewModel() {
    private val TAG = "ViewModel"
    private val API_KEY = "aabe408a0amsh150fbff3bc2552dp1e0eabjsnf8e226a892cd"
    private var _allSymptoms = MutableLiveData<List<Symptoms>>()
    val allSymptoms: LiveData<List<Symptoms>>
    get() = _allSymptoms

    private var _allDiagnosis = MutableLiveData<List<Diagnosis>>()
    val allDiagnosis: LiveData<List<Diagnosis>>
        get() = _allDiagnosis


    fun getAllSymptoms(language:String) {
        ApiMedicApi.retrofitService.getAllSymptoms(language, API_KEY).enqueue(object : Callback<List<Symptoms>> {
            override fun onResponse(
                call: Call<List<Symptoms>>,
                response: Response<List<Symptoms>>
            ) {
                val body = response.body()
                _allSymptoms.value = body!!
            }
            override fun onFailure(call: Call<List<Symptoms>>, t: Throwable) {
                Log.e(TAG, "Failure: ${t.message}")
            }
        })

    }

    fun getDiagnosis (gender:String, yearOfBirth: Int, symptoms: String, language:String) {
        ApiMedicApi.retrofitService.getDiagnosis(gender, yearOfBirth, symptoms, language, API_KEY).enqueue(object : Callback<List<Diagnosis>> {
                override fun onResponse(
                    call: Call<List<Diagnosis>>,
                    response: Response<List<Diagnosis>>
                ) {
                    val body = response.body()
                    _allDiagnosis.value = body!!
                }
                override fun onFailure(call: Call<List<Diagnosis>>, t: Throwable) {
                    Log.e(TAG, "Failure: ${t.message}")
                }
            })
    }

}