package com.woojjajja.myboardactivity.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woojjajja.myboardactivity.data.Board
import com.woojjajja.myboardactivity.data.BoardDatabase

class BoardViewModel(application: Application) : AndroidViewModel(application) {

    private var boardData: MutableList<Board>? = null
    var boardLiveData = MutableLiveData<MutableList<Board>>()

    init {
        boardData = BoardDatabase.getInstance(application.applicationContext).selectAll()
        boardLiveData.value = boardData
    }

    fun getBoardList(): MutableList<Board>? {
        return boardData
    }

    fun addBoard(board: Board){
        boardData?.add(board)
        boardLiveData.value = boardData
    }

    fun setBoard(board: Board){
        boardData?.map {
            if(it.id == board.id){
                it.title = board.title
            }
        }
        boardLiveData.value = boardData
    }

    fun removeBoard(id: Int){
        boardData?.removeAll {
            it.id == id
        }
        boardLiveData.value = boardData
    }
}