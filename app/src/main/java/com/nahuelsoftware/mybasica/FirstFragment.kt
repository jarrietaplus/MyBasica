package com.nahuelsoftware.mybasica

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.nahuelsoftware.mybasica.databinding.FragmentFirstBinding
import kotlinx.coroutines.NonDisposableHandle.dispose
import kotlin.system.exitProcess

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textviewFirst.setText("No hay selección")

        val options = arrayOf("Clarin","El País","El Mundo")
        var defaultPosition = -1 //no hay seleccion :-)
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setTitle("Seleccionar periódico")
        dialogBuilder.setPositiveButton(getString(android.R.string.ok)) { dialog, _ -> dialog.dismiss() }
        dialogBuilder.setSingleChoiceItems(options, defaultPosition) { dialog, which ->
            defaultPosition = which
            when (options[which]) {
                "Clarin" -> {
                    binding.textviewFirst.setText("Diario Clarin")
                    BASE_URL = "http://www.clarin.com"
                }
                "El País" -> {
                    binding.textviewFirst.setText("Diario El País")
                    BASE_URL = "http://elpais.com"
                }
                "El Mundo" -> {
                    binding.textviewFirst.setText("Diario El Mundo")
                    BASE_URL = "http://www.elmundo.es"
                }
            }
        }
        dialogBuilder.show()

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.buttonReturn.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_mainActivity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}