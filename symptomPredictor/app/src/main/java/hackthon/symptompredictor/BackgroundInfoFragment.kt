package hackthon.symptompredictor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import hackthon.symptompredictor.databinding.FragmentSymptomCheckerBinding

class SymptomCheckerFragment : Fragment() {
    private val FRAGMENT = "SYMPTOM_CHECKER_FRAGMENT"
    private var _binding: FragmentSymptomCheckerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var rootView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView =  inflater.inflate(R.layout.fragment_symptom_checker, container, false)

        // chips onClickListener
        val maleChip = rootView.findViewById<Chip>(R.id.male_chip)
        val femaleChip = rootView.findViewById<Chip>(R.id.female_chip)
        val otherChip = rootView.findViewById<Chip>(R.id.other_chip)
        val yesChip = rootView.findViewById<Chip>(R.id.yes_chip)
        val noChip = rootView.findViewById<Chip>(R.id.no_chip)

        selectUnselectColor(maleChip)
        selectUnselectColor(femaleChip)
        selectUnselectColor(otherChip)
        selectUnselectColor(yesChip)
        selectUnselectColor(noChip)

        // age range dropdown
        val ageRangeOptions = resources.getStringArray(R.array.age_range_options)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_list_item, ageRangeOptions)
        val autocompleteTV = rootView.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        autocompleteTV.setAdapter(arrayAdapter)

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}