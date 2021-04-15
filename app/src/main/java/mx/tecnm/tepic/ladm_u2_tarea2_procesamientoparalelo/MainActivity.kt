package mx.tecnm.tepic.ladm_u2_tarea2_procesamientoparalelo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

var restart: Boolean = true
var stop: Boolean = true
var pausa: Boolean = true

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.max = 1000
        principal.setOnClickListener {
            var hilo = Hilo(this)
            hilo.start()
        }
        pausar.setOnClickListener {
            if (pausa) {
                pausar.setText("Reanudar")
                pausa = false
            } else {
                pausar.setText("Pausar")
                pausa = true
            }
        }
        reinicio.setOnClickListener {
            restart = false
        }
        detener.setOnClickListener {
            stop = false
            restart = false

        }
    }
}

class Hilo(act: MainActivity) : Thread() {
    var activity = act
    var progreso: Int = 0
    override fun run() {
        super.run()
        while (stop) {
            progreso = 0
            activity.progressBar.progress = progreso
            while (restart) {
                if (pausa) {
                    progreso += Random().nextInt(70)
                    activity.progressBar.progress = progreso
                    print(progreso)
                    sleep(1000)
                } else {
                    sleep(600)
                }
            }
            restart = true
            pausa = true
            sleep(400)
        }
        progreso = 0
        activity.progressBar.progress = progreso
        pausa = true
        restart = true
        stop = true
    }
}