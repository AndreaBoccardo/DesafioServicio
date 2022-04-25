package cl.desafiolatm.desafioservicio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import cl.desafiolatm.desafioservicio.databinding.ActivityMainBinding
import cl.desafiolatm.desafioservicio.service.ContadorServicio

class MainActivity : AppCompatActivity(), Handler.Callback {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        with(binding){
            btnIniciar.setOnClickListener {
                if(ContadorServicio.running)
                {
                    ContadorServicio.stopService(this@MainActivity)
                }
                else
                {
                    ContadorServicio.startService(this@MainActivity,"Iniciando",
                        Handler(mainLooper,this@MainActivity))
                }
            }


        }

        setContentView(binding.root)
    }

    override fun handleMessage(msg: Message): Boolean {
        val contador = msg.data.getString("Contador")
        binding.etContador.text = contador.toString()
        return true
    }
}