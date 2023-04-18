package com.example.myqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myqr.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnScanner.setOnClickListener{ initScanner()}
    }
    private fun initScanner(){

        val tipoScanner = IntentIntegrator(this)
        tipoScanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        tipoScanner.setPrompt("Escaneando")
        tipoScanner.setTorchEnabled(false)
        tipoScanner.setBeepEnabled(true)
        tipoScanner.initiateScan()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if(result !=null){
            if(result.contents == null){
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Resultado: {${result.contents}}", Toast.LENGTH_SHORT).show()
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)

        }


    }
}