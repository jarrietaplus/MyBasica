package com.nahuelsoftware.mybasica

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import com.nahuelsoftware.mybasica.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        binding.textviewSecond.setText("charleta")
*/
        //detecto el widget visor
//        val webView: WebView = rootView.findViewById(R.id.webViewHelp)
        //habilito su cliente Chrome - necesario -
        binding.webViewHelp.webChromeClient = object : WebChromeClient() {
        }
        //habilito su cliente View - necesario -
        binding.webViewHelp.webViewClient = object : WebViewClient() {
            //cuando se carga una pagina, pido su url al visor y lo refresco en el buscador
            override fun onPageStarted(view: WebView?, url: String, favicom: Bitmap?) {
                super.onPageStarted(view, url, favicom)
//                searchView.setQuery(url, false)
            }
        }
        //habilito javaScript - necesario - y cargo la pagina de inicio
        val settings: WebSettings = binding.webViewHelp.settings
        settings.javaScriptEnabled= true
        if (BASE_URL != "") binding.webViewHelp.loadUrl(BASE_URL)
        else binding.webViewHelp.loadUrl("www.nahuel.com")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}