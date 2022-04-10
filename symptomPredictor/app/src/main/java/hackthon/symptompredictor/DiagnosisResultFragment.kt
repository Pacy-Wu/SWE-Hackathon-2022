package hackthon.symptompredictor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import java.lang.StringBuilder

class DiagnosisResultFragment : Fragment() {
    private val TAG = "DiagnosisResultFragment"
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_diagnosis_result, container, false)


        rootView.findViewById<TextView>(R.id.back_btn).setOnClickListener {
            findNavController().popBackStack()
        }

        return rootView
    }

}