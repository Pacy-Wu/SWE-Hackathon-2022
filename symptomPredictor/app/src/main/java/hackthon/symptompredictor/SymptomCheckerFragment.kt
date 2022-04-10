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


class SymptomCheckerFragment : Fragment() {
    private val TAG = "SymptomCheckerFragment"
    private lateinit var rootView: View
    private lateinit var viewModel: SymptomDiseaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_symptom_checker, container, false)


        val autocompleteTV = rootView.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView_symptoms)

        // TODO api get all symptoms
        viewModel = ViewModelProvider(this).get(SymptomDiseaseViewModel::class.java)


        val symptomsOptions = arrayOf("Symptom 1", "Symptom 2", "Symptom 3", "Symptom 4", "Symptom 4", "Symptom 4", "Symptom 4")
        val checkedItems = mutableListOf<Boolean>()
        for (i in 1..symptomsOptions.size) {
            checkedItems.add(false)
        }

        autocompleteTV.setOnClickListener {
            val dialog = SymptomChoicesDialogFragment(checkedItems, symptomsOptions)
            dialog.show(requireActivity().supportFragmentManager, TAG)
        }

        // chips onClickListener
        val todayChip = rootView.findViewById<Chip>(R.id.today_chip)
        val inAMonthChip = rootView.findViewById<Chip>(R.id.in_a_month_chip)
        val inAWeekChip = rootView.findViewById<Chip>(R.id.in_a_week_chip)
        val overAMonthChip = rootView.findViewById<Chip>(R.id.over_a_month_chip)

        selectUnselectColor(todayChip)
        selectUnselectColor(inAMonthChip)
        selectUnselectColor(inAWeekChip)
        selectUnselectColor(overAMonthChip)

        rootView.findViewById<TextView>(R.id.back_btn).setOnClickListener {
            findNavController().popBackStack()
        }

        rootView.findViewById<Button>(R.id.search_disease_btn).setOnClickListener {
            // TODO
//            findNavController().navigate();
        }

        return rootView
    }

    private fun selectUnselectColor(chip: Chip) {
        chip.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                chip.setChipBackgroundColorResource(R.color.dark_blue)
            } else {
                chip.setChipBackgroundColorResource(R.color.transparent_dark_blue)
            }
        }
    }

}