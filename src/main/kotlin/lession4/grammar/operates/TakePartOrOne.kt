package lession4.grammar.operates

fun main(){
    displayRandom()
}


fun displaySlice(){
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.slice(1..3))
    println(numbers.slice(0..4 step 2))
    println(numbers.slice(setOf(3, 5, 0)))
    println(numbers.slice(listOf(3,1,4,1, 5,0)))
}

/**
 * 取越界是全部取出
 * 删越界是全部删除
 * 传负数会崩溃
 * */
fun displayTakeAndDrop(){
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.take(3))
    //取越界
    println(numbers.take(13))
    println(numbers.takeLast(3))
    println(numbers.drop(1))
    //删除越界
    println(numbers.dropLast(15))
}

fun displayTakeAndDropWhile(){
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.takeWhile { !it.startsWith('f') })
    println(numbers.takeLastWhile { it != "three" })
    println(numbers.dropWhile { it.length == 3 })
    println(numbers.dropLastWhile { it.contains('i') })
}


fun displayChunked(){
    val numbers = (0..13).toList()
    println(numbers.chunked(3))
}

fun displayChunked2(){
    val numbers = (0..13).toList()
    println(numbers.chunked(3) { it.sum() })
}

fun displayWindow(){
    val numbers = listOf("one", "two", "three", "four", "five")
    println(numbers.windowed(3))
}

/**
 * step 定义两个相邻窗口的第一个元素之间的距离。默认情况下，该值为 1，因此结果包含从所有元素开始的窗口。如果将 step 增加到 2，将只收到以奇数元素开头的窗口：第一个、第三个等。
 * partialWindows 包含从集合末尾的元素开始的较小的窗口。例如，如果请求三个元素的窗口，就不能为最后两个元素构建它们。在本例中，启用 partialWindows 将包括两个大小为2与1的列表。
 * */
fun displayWindow2(){
    val numbers = (1..10).toList()
    println(numbers.windowed(3, step = 2, partialWindows = true))
    println(numbers.windowed(3) { it.sum() })
}

fun displayWindow3(){
    val numbers = listOf("one", "two", "three", "four", "five")
    println(numbers.zipWithNext())
    println(numbers.zipWithNext() { s1, s2 -> s1.length > s2.length})
}

fun displayElementAt(){
    val numbers = linkedSetOf("one", "two", "three", "four", "five")
    println(numbers.elementAt(3))

    val numbersSortedSet = sortedSetOf("one", "two", "three", "four")
    println(numbersSortedSet.elementAt(0)) // 元素以升序存储
}

fun displayFirstLast(){
    val numbers = listOf("one", "two", "three", "four", "five")
    println(numbers.first())
    println(numbers.last())
}

fun displayElementAtOr(){
    val numbers = listOf("one", "two", "three", "four", "five")
    println(numbers.elementAtOrNull(5))
    println(numbers.elementAtOrElse(5) { index -> "The value for index $index is undefined"})
}

fun displayLastOrFirst(){
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.first { it.length > 3 })
    println(numbers.last { it.startsWith("f") })
}

fun displayLastOrFirstNull(){
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.firstOrNull { it.length > 6 })
}

fun displayFind(){
    val numbers = listOf(1, 2, 3, 4)
    println(numbers.find { it % 2 == 0 })
    println(numbers.findLast { it % 2 == 0 })
}

fun displayRandom(){
    val numbers = listOf(1, 2, 3, 4)
    println(numbers.random())
}

fun displayContains(){
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.contains("four"))
    println("zero" in numbers)

    println(numbers.containsAll(listOf("four", "two")))
    println(numbers.containsAll(listOf("one", "zero")))
}

fun displayEmpty(){

    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.isEmpty())
    println(numbers.isNotEmpty())

    val empty = emptyList<String>()
    println(empty.isEmpty())
    println(empty.isNotEmpty())

}
