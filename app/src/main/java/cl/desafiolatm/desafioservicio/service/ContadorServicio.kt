package cl.desafiolatm.desafioservicio.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import cl.desafiolatm.desafioservicio.MainActivity
import cl.desafiolatm.desafioservicio.R

class ContadorServicio: Service() {

    private var mHandler: Handler = Handler(Looper.getMainLooper())
    private lateinit var mRunnable: Runnable
    var contador = 0

    companion object{
        var running = false
        private lateinit var handleCallback: Handler

        fun startService(context: Context, mensaje:String, handler: Handler)
        {
            val startIntento = Intent(context,ContadorServicio::class.java)
            startIntento.putExtra("inputExtra",mensaje)
            ContextCompat.startForegroundService(context,startIntento)
            running = true
            handleCallback = handler
        }

        fun stopService(context: Context)
        {
            val stopIntento = Intent(context, ContadorServicio::class.java)
            context.stopService(stopIntento)
            running = false
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Log.i("SERVICIO CONTADOR","CREADO")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("SERVICIO CONTADOR","INICIADO")
        val input = intent?.getStringExtra("inputExtra")?:""
        crearCanal(input)
        runTask()
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        Log.i("SERVICIO CONTADOR","DESTRUIDO")
        mHandler.removeCallbacks(mRunnable)
        super.onDestroy()
    }

    private fun crearCanal(input: String)
    {
        val canal = "CANAL_CONTADOR"
        //canal de notificación
        val nc: NotificationChannel
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nc = NotificationChannel(canal,"Canal Servicio contador", NotificationManager.IMPORTANCE_NONE)
            nm.createNotificationChannel(nc)
        }
        val intento = Intent(this, MainActivity::class.java)
        val pendiente = PendingIntent.getActivity(this,0,intento,0)
        val notificacion = NotificationCompat.Builder(this,canal)
            .setContentTitle("Contador!")
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_baseline_add_alert_24)
            .setContentIntent(pendiente)
            .build()
        startForeground(1,notificacion)
    }

    private fun runTask()
    {
        val delayTime = 1000*7L
        mRunnable = Runnable {
            contador++
            notifyNextEvent()
            mHandler.postDelayed(mRunnable,delayTime)
        }
        mHandler.postDelayed(mRunnable,delayTime)
    }

    private fun notifyNextEvent()
    {
        Log.i("SERVICIO CONTADOR","Run Task: $contador")
        val mensaje = handleCallback.obtainMessage(1,"msg")
        mensaje.data.putString("Contador",contador.toString())
        mensaje.sendToTarget()
    }
}