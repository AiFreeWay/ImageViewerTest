package testapplication.imageviewer.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import testapplication.imageviewer.presentation.ui.main.views.activities.MainActivity

/**
 * Created by root on 14.08.17.
 */
class SlpashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}