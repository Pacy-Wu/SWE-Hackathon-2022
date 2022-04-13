package hackthon.symptompredictor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import hackthon.symptompredictor.network.Diagnosis
import hackthon.symptompredictor.network.Symptoms
import java.lang.StringBuilder
import androidx.navigation.fragment.navArgs


class DiagnosisResultFragment : Fragment() {
    private val TAG = "DiagnosisResultFragment"
    private lateinit var rootView: View
    private lateinit var viewModel: SymptomDiseaseViewModel
    private var gender: String = ""
    private var language = ""
    private var symptoms = ""
    private var yearOfBirth = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: DiagnosisResultFragmentArgs by navArgs()
        gender = args.gender
        language = args.languages
        symptoms = args.symptoms
        yearOfBirth = args.yearOfBirth

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_diagnosis_result, container, false)

        viewModel = ViewModelProvider(this).get(SymptomDiseaseViewModel::class.java)
        val allDiagnosisObserver = Observer<List<Diagnosis>>{
            if (it.size == 0) {
                rootView.findViewById<TextView>(R.id.top_diagnosis_title).text = "Can't find issue"
                rootView.findViewById<TextView>(R.id.other_diagnosis_content).text = "Can't find issue"
            } else {

                rootView.findViewById<TextView>(R.id.top_diagnosis_title).text = it[0].issue.name
                var otherIssue = ""
                for (i in it) {
                    if (i.toString() != it[0].toString()) {
                        otherIssue += i.issue.name + ", "
                    }
                }
                otherIssue = otherIssue.substring(0, otherIssue.length - 2)
                rootView.findViewById<TextView>(R.id.other_diagnosis_content).text = otherIssue
                Log.v(TAG, it.toString())
            }
        }

        Log.v(TAG, symptoms)
        viewModel.allDiagnosis.observe(viewLifecycleOwner, allDiagnosisObserver)
        viewModel.getDiagnosis(gender, yearOfBirth, symptoms, language)

        rootView.findViewById<TextView>(R.id.back_btn).setOnClickListener {
            findNavController().popBackStack()
        }

        return rootView
    }

}