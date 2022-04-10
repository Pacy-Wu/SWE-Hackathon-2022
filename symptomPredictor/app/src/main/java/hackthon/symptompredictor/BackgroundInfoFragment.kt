package hackthon.symptompredictor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import hackthon.symptompredictor.databinding.FragmentBackgroundInfoBinding

class BackgroundInfoFragment : Fragment() {
    private val FRAGMENT = "BACKGROUND_INFO_FRAGMENT"
    private var _binding: FragmentBackgroundInfoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var rootView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView =  inflater.inflate(R.layout.fragment_background_info, container, false)

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
        val ageRangeOptions =  arrayOf("0 - 10", "10 - 20", "20 - 30", "30 - 40", "40 - 50", "50 - 65", "Above 65")
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_list_item, ageRangeOptions)
        val autocompleteTV = rootView.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView_age_range)
        autocompleteTV.setAdapter(arrayAdapter)

        // next button
        rootView.findViewById<Button>(R.id.next_btn).setOnClickListener {
            findNavController().navigate(R.id.action_BackgroundInfoFragment_to_SymptomCheckerFragment)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}