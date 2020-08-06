package com.woojjajja.myboardactivity.ui

import android.app.Activity
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.woojjajja.myboardactivity.R
import com.woojjajja.myboardactivity.data.Board
import com.woojjajja.myboardactivity.util.AppLogger
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

class ViewActivity : BaseActivity() {

    private var board: Board? = null

    override fun getLayoutID() = R.layout.layout_view

    override fun getAppTitle() = "View"

    override fun setUp() {

        var textTitle = findViewById<TextView>(R.id.text_title)

        intent?.run {
            board = getSerializableExtra(ListActivity._DATA) as Board

            textTitle.tag = board?.id
            textTitle.text = board?.title
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_board,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_modify -> {
                val i = Intent()
                i.putExtra(ListActivity._DATA,board)
                setResult(Activity.RESULT_FIRST_USER,i)
                finish()
                true
            }
            R.id.menu_remove -> {
                alert("삭제하시겠습니까?") {
                    yesButton {
                        val i = Intent()
                        i.putExtra(ListActivity._ID,board?.id)
                        setResult(Activity.RESULT_OK,i)
                        finish()
                    }
                }.show()

                true
            }
        }
        return false
        //return super.onOptionsItemSelected(item)
    }
}