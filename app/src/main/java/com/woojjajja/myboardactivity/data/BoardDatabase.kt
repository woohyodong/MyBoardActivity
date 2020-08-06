package com.woojjajja.myboardactivity.data

import android.content.Context
import androidx.lifecycle.MutableLiveData

class BoardDatabase : BoardDao {

    private lateinit var boardData: MutableList<Board>

    init {
        boardData = mutableListOf(Board(1,"테스트입니다."), Board(2,"샘플입니다."))
    }

    companion object{
        @Volatile
        private var INSTANCE: BoardDatabase? = null

        fun getInstance(context: Context): BoardDatabase{
            return INSTANCE ?: synchronized(this){
                var instance = BoardDatabase()
                INSTANCE = instance
                instance
            }
        }
    }

    override fun selectAll(): MutableList<Board> {
        return boardData
    }

    override fun select(id: Int): Board {
        return boardData.filter {
            it.id == id
        }.first()
    }

    override fun insert(board: Board) {
        boardData.add(board)
    }

    override fun delete(id: Int) {
        boardData.removeAll {
            it.id == id
        }
    }

    override fun update(board: Board) {
        boardData.map {
            if(it.id == board.id){
                it.title = board.title
            }
        }
    }
}