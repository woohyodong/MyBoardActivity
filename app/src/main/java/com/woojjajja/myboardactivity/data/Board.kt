package com.woojjajja.myboardactivity.data

import com.woojjajja.myboardactivity.util.CodeHelper
import java.io.Serializable

data class Board(val id: Int = CodeHelper.rand(1, 1000), var title: String = ""): Serializable