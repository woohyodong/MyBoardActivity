package com.woojjajja.myboardactivity.data

interface BoardDao {

    fun selectAll(): MutableList<Board>

    fun select(id: Int) : Board

    fun insert(board: Board)

    fun delete(id: Int)

    //etc
    fun update(board: Board)
}