package com.woojjajja.myboardactivity.ui

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.widget.EditText
import com.woojjajja.myboardactivity.R
import com.woojjajja.myboardactivity.data.Board
import kotlinx.android.synthetic.main.layout_edit.*

class EditActivity : BaseActivity() {

    private var board: Board? = null

    override fun getLayoutID() = R.layout.layout_edit

    override fun getAppTitle() = "Edit"

    override fun setUp() {

        val editTile = findViewById<EditText>(R.id.edit_title)

        intent?.run {
            getSerializableExtra(ListActivity._DATA)?.let {
                board = it as Board

                editTile.tag = board?.id
                editTile.setText(board?.title)
            }

        }

        button_add.setOnClickListener {

            if(!TextUtils.isEmpty(editTile.text)){

                board = board ?: Board()
                board?.title = editTile.text.toString()

                val i = Intent()
                i.putExtra(ListActivity._DATA,board)
                setResult(Activity.RESULT_OK,i)

                finish()
            }
        }
    }
}