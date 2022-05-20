package com.nahuelsoftware.mybasica

import android.media.AudioManager
import android.media.AudioManager.FLAG_PLAY_SOUND
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

class Activity5 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_5)

        val barraVolumen1: SeekBar = this.findViewById(R.id.barraVolumen1)
        val btnDownVol1: Button = this.findViewById(R.id.buttonDown1)
        val btnUpVol1: Button = this.findViewById(R.id.buttonUp1)
        val barraVolumen2: SeekBar = this.findViewById(R.id.barraVolumen2)
        val btnDownVol2: Button = this.findViewById(R.id.buttonDown2)
        val btnUpVol2: Button = this.findViewById(R.id.buttonUp2)
        val barraVolumen3: SeekBar = this.findViewById(R.id.barraVolumen3)
        val btnDownVol3: Button = this.findViewById(R.id.buttonDown3)
        val btnUpVol3: Button = this.findViewById(R.id.buttonUp3)

        val audioManager: AudioManager = getSystemService(AUDIO_SERVICE) as AudioManager
//CLICK
//        barraVolumen1.setEnabled(false)
        barraVolumen1.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION))
        barraVolumen1.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION))

        //boton bajar volúmen
        btnDownVol1.setOnClickListener {
            audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND)
            barraVolumen1.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION))
            System.out.println("VOL1: ${audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION)}")
        }

        //boton subir volúmen
        btnUpVol1.setOnClickListener {
            audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND)
            barraVolumen1.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION))
            System.out.println("VOL1: ${audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION)}")
        }
//MUSICA
        barraVolumen2.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC))
        barraVolumen2.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC))

        //boton bajar volúmen
        btnDownVol2.setOnClickListener {
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, FLAG_PLAY_SOUND)
            barraVolumen2.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC))
            System.out.println("VOL2: ${audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)}")
        }

        //boton subir volúmen
        btnUpVol2.setOnClickListener {
            audioManager.adjustStreamVolume( AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, FLAG_PLAY_SOUND)
            barraVolumen2.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC))
            System.out.println("VOL2: ${audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)}")
        }

//ALARMA
        barraVolumen3.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM))
        barraVolumen3.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_ALARM))

        //boton bajar volúmen
        btnDownVol3.setOnClickListener {
            audioManager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_LOWER, FLAG_PLAY_SOUND)
            barraVolumen3.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_ALARM))
            System.out.println("VOL3: ${audioManager.getStreamVolume(AudioManager.STREAM_ALARM)}")
        }

        //boton subir volúmen
        btnUpVol3.setOnClickListener {
            audioManager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_RAISE, FLAG_PLAY_SOUND)
            barraVolumen3.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_ALARM))
            System.out.println("VOL3: ${audioManager.getStreamVolume(AudioManager.STREAM_ALARM)}")
        }

    }
}