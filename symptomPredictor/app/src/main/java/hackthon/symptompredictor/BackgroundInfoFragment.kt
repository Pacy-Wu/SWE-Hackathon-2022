package hackthon.symptompredictor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import hackthon.symptompredictor.databinding.FragmentBackgroundInfoBinding

class BackgroundInfoFragment : Fragment() {
    private val FRAGMENT = "BACKGROUND_INFO_FRAGMENT"
    private var _binding: FragmentBackgroundInfoBinding? = null
    private val API_KEY = "aabe408a0amsh150fbff3bc2552dp1e0eabjsnf8e226a892cd"
    private val TAG = "BackgroundInfoFragment"

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

        // next button
        rootView.findViewById<Button>(R.id.next_btn).setOnClickListener {
            val genderChipGroup = rootView.findViewById<ChipGroup>(R.id.gender_chipgroup)
            val gender = rootView.findViewById<Chip>(genderChipGroup.checkedChipId).text.toString()
            val yearOfBirth = rootView.findViewById<EditText>(R.id.age_input).text.toString().toInt()
            val action = BackgroundInfoFragmentDirections.actionBackgroundInfoFragmentToSymptomCheckerFragment(gender, yearOfBirth)
            Log.v(TAG, gender + ", " + yearOfBirth)
            findNavController().navigate(action)
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