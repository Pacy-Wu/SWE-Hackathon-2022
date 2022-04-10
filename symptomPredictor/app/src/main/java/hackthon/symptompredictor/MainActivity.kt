package hackthon.symptompredictor

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import hackthon.symptompredictor.databinding.ActivityMainBinding
import hackthon.symptompredictor.network.ApiMedicApi
import hackthon.symptompredictor.network.Diagnosis
import hackthon.symptompredictor.network.Symptoms
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val TAG = "MainActivity"
    private val API_KEY = "aabe408a0amsh150fbff3bc2552dp1e0eabjsnf8e226a892cd"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set the action bar elevation to zero
        supportActionBar?.elevation = 0F

//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)


    }

//    private fun test() {
//        // get the data for recipe's ingredients
//        ApiMedicApi.retrofitService.getDiagnosis("male", 1984, "[234,11]", "en-gb", API_KEY)
//            .enqueue(object : Callback<List<Diagnosis>> {
//                override fun onResponse(
//                    call: Call<List<Diagnosis>>,
//                    response: Response<List<Diagnosis>>
//                ) {
//                    val body = response.body()
//                    Log.v(TAG, "RETURNED INFO:")
//                    Log.v(TAG, "$body")
//                }
//                override fun onFailure(call: Call<List<Diagnosis>>, t: Throwable) {
//                    Log.e(TAG, "Failure: ${t.message}")
//                }
//            })
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}