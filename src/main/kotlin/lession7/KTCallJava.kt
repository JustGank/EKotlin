package lession5.grammar

import java.util.*

fun main() {

}

fun testGetSet() {
    val calendar = Calendar.getInstance()
    if (calendar.firstDayOfWeek == Calendar.SUNDAY) {  // 调用 getFirstDayOfWeek()
        calendar.firstDayOfWeek = Calendar.MONDAY      // 调用ll setFirstDayOfWeek()
        if (!calendar.isLenient) {                         // 调用 isLenient()
            calendar.isLenient = true                      // 调用 setLenient()
        }
    }




}

