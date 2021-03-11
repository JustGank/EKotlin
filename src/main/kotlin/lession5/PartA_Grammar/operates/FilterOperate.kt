package lession5.PartA_Grammar.operates

fun main(){
    testCheck()
}

fun displayFilter(){
    val numbers = listOf("one", "two", "three", "four")
    val longerThan3 = numbers.filter { it.length > 3 }
    println(longerThan3)

    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    val filteredMap = numbersMap.filter { (key, value) -> key.endsWith("1") && value > 10}
    println(filteredMap)
}

fun displayFilterIsInstance(){
    val numbers = listOf(null, 1, "two", 3.0, "four")
    println("All String elements in upper case:")
    numbers.filterIsInstance<String>().forEach {
        println(it.toUpperCase())
    }
}

fun testPartition(){
    val numbers = listOf("one", "two", "three", "four")
    //这是一个 解构 操作 有时把一个对象 解构 成很多变量会很方便
    val (match , rest) = numbers.partition { it.length > 3 }

    println(match)
    println(rest)
}

fun testCheck(){
    val numbers = listOf("one", "two", "three", "four")

    println(numbers.any { it.endsWith("e") })
    println(numbers.none { it.endsWith("a") })
    println(numbers.all { it.endsWith("e") })

    println(emptyList<Int>().all { it > 5 })
}

fun testCheck2(){
    val numbers = listOf("one", "two", "three", "four")
    val empty = emptyList<String>()

    println(numbers.any())
    println(empty.any())

    println(numbers.none())
    println(empty.none())
}