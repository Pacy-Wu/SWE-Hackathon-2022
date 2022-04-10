package hackthon.symptompredictor

import hackthon.symptompredictor.R
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.util.Log
import android.widget.AutoCompleteTextView
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class SymptomChoicesDialogFragment(val checkedItems: MutableList<Boolean>, val symptomsOptions: ArrayList<String>) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val symptomsChecked = mutableListOf<String>()

        return MaterialAlertDialogBuilder(this.requireContext())
            .setTitle("Symptoms")
            .setMultiChoiceItems(symptomsOptions.toTypedArray(), checkedItems.toBooleanArray()) { dialog, item, isChecked ->
                val symptomClick: String = symptomsOptions[item]
                if (isChecked) {
                    symptomsChecked.add(symptomClick)
                    checkedItems[item] = true
                } else {
                    symptomsChecked.remove(symptomClick)
                    checkedItems[item] = false
                }
                Log.v("test", checkedItems.toString())
            }
            .setNegativeButton("Cancel") { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton("OK") { dialog, which ->
                // Respond to positive button press
                requireActivity().findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView_symptoms)
                    .setText(symptomsChecked.toString().substring(1, symptomsChecked.toString().length - 1))

            }.show()

    }


}

