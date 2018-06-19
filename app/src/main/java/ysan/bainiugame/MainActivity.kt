package ysan.bainiugame

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val mSbView by lazy { find<SBview>(R.id.main_sb) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btStart = findViewById<Button>(R.id.bt_start)
        btStart.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_start -> mSbView.setStart(true)
        }
    }
}
