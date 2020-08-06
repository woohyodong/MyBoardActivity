package com.woojjajja.myboardactivity.ui

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.woojjajja.myboardactivity.R
import com.woojjajja.myboardactivity.data.Board
import com.woojjajja.myboardactivity.data.BoardDataAdapter
import com.woojjajja.myboardactivity.data.BoardDatabase
import com.woojjajja.myboardactivity.util.CodeHelper
import kotlinx.android.synthetic.main.layout_list.*

class ListActivity : BaseActivity() {

    lateinit var boardDatabase: BoardDatabase
    lateinit var boardDataAdapter: BoardDataAdapter
    private val editActivityRequestCode = 100
    private val editActivityRequestCode2 = 101
    private val viewActivityRequestCode = 102

    //#4step => LiveData + observe + ViewModel
    private lateinit var boardViewModel: BoardViewModel

    companion object{
        const val _DATA = "_DATA"
        const val _ID = "_ID"
    }

    override fun getLayoutID() = R.layout.layout_list

    override fun getAppTitle() = "List"

    override fun setUp() {

        //log 확인용
        edit_log.setText(CodeHelper.getTimeStamp("yyyy-MM-dd HH:mm:ss")+" 실행")

        //#4step => LiveData + observe + ViewModel
        boardViewModel = ViewModelProvider(this).get(BoardViewModel::class.java)

        boardViewModel.boardLiveData.observe(this, Observer {
            boardDataAdapter.setBoardData(it)
        })

        boardDataAdapter = BoardDataAdapter(this@ListActivity){item ->
            //AppLogger.toast("click $item")
            var i = Intent(this@ListActivity, ViewActivity::class.java)
            i.putExtra(_DATA, item)
            startActivityForResult(i,viewActivityRequestCode)
        }

        recyclerview_list.apply {
            adapter = boardDataAdapter
            setHasFixedSize(true)
        }

        button_fab.setOnClickListener {
            var i = Intent(this@ListActivity, EditActivity::class.java)
            startActivityForResult(i,editActivityRequestCode)
        }

        boardViewModel.getBoardList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                editActivityRequestCode -> { //등록
                    data?.let {
                        var item = it.getSerializableExtra(_DATA) as Board
                        //boardDatabase.insert(item)
                        //1단계
                        //boardDataAdapter.setBoardData(boardDatabase.selectAll())

                        //#4step => LiveData + observe + ViewModel
                        boardViewModel.addBoard(item)
                    }
                }
                viewActivityRequestCode -> { //삭제
                    data?.let {
                        var id = it.getIntExtra(_ID,0)
                        //boardDatabase.delete(id)
                        //1단계
                        //boardDataAdapter.setBoardData(boardDatabase.selectAll())
                        //#4step => LiveData + observe + ViewModel
                        boardViewModel.removeBoard(id)
                    }
                }
                editActivityRequestCode2 -> { //수정

                    data?.let {
                        var item = it.getSerializableExtra(_DATA) as Board

                        //boardDatabase.update(item)
                        //1단계
                        //boardDataAdapter.setBoardData(boardDatabase.selectAll())

                        //#4step => LiveData + observe + ViewModel
                        boardViewModel.setBoard(item)
                    }

                }
            }
        }

        if(resultCode == Activity.RESULT_FIRST_USER){ //수정
            var i = Intent(this@ListActivity, EditActivity::class.java)
            i.putExtra(_DATA, data?.getSerializableExtra(_DATA))
            startActivityForResult(i,editActivityRequestCode2)
        }
    }
}