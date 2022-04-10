package hackthon.symptompredictor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_home, container, false)

        rootView.findViewById<TextView>(R.id.symptom_checker_btn).setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_BackgroundInfoFragment)
        }

        rootView.findViewById<TextView>(R.id.bmi_calculator_btn).setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_BMICalculatorFragment)
        }

        rootView.findViewById<TextView>(R.id.medical_health_news_btn).setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_MedicalNewsFragment)
        }


        return rootView
    }
}