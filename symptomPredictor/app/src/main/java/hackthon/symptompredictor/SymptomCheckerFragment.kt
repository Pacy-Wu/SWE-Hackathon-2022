package hackthon.symptompredictor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import hackthon.symptompredictor.network.Symptoms
import java.lang.StringBuilder


class SymptomCheckerFragment : Fragment() {
    private val TAG = "SymptomCheckerFragment"
    private lateinit var rootView: View
    private lateinit var viewModel: SymptomDiseaseViewModel

    private var gender: String = ""
    private var yearOfBirth = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: SymptomCheckerFragmentArgs by navArgs()
        gender = args.gender
        yearOfBirth = args.yearOfBirth

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_symptom_checker, container, false)
        var symptomsOptions = listOf<Symptoms>()
        var symptomsOptionsInString = arrayListOf<String>()

        val autocompleteTV = rootView.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView_symptoms)

        val symptomsToId = hashMapOf<String, Int>()
        viewModel = ViewModelProvider(this).get(SymptomDiseaseViewModel::class.java)
        val checkedItems = mutableListOf<Boolean>()
        val allSymptomObserver = Observer<List<Symptoms>>{
            symptomsOptions = it
            checkedItems.clear()
            symptomsOptionsInString.clear()
            for (item in symptomsOptions) {
                symptomsOptionsInString.add(item.name)
                symptomsToId.put(item.name, item.id)
            }

            for (i in 1..symptomsOptions.size) {
                checkedItems.add(false)
            }


            Log.v("Test", symptomsOptionsInString.toString())
        }

        viewModel.allSymptoms.observe(viewLifecycleOwner, allSymptomObserver)
        viewModel.getAllSymptoms("en-gb")

        autocompleteTV.setOnClickListener {
            val dialog = SymptomChoicesDialogFragment(checkedItems, symptomsOptionsInString)
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
            val checkedItemsList = mutableListOf<Int>()
            var index = 0
            for (item in checkedItems) {
                if (item == true) {

                    checkedItemsList.add(symptomsToId.get(symptomsOptionsInString[index])!!)
                }
                index++
            }

            val action = SymptomCheckerFragmentDirections.actionSymptomCheckerFragmentToDiagnosisResultFragment(gender, yearOfBirth, checkedItemsList.toList().toString(), "en-gb")
            findNavController().navigate(action);
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