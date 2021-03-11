package lession5.PartA_Grammar.operates

fun main(){
    testMinusAssign()
}

fun testAdd(){
    val numbers = mutableListOf(1, 2, 3, 4)
    numbers.add(5)
    println(numbers)
}

fun testAddAll(){
    val numbers = mutableListOf(1, 2, 5, 6)
    numbers.addAll(arrayOf(7, 8))
    println(numbers)
    numbers.addAll(2, setOf(3, 4))
    println(numbers)
}

fun testPlusAssign(){
    val numbers = mutableListOf("one", "two")
    numbers += "three"
    println(numbers)
    numbers += listOf("four", "five")
    println(numbers)
}

fun testDeleteItem(){
    val numbers = mutableListOf(1, 2, 3, 4, 3)
    numbers.remove(3)                    // 删除了第一个 `3`
    println(numbers)
    numbers.remove(5)                    // 什么都没删除
    println(numbers)
}

fun testDeleteItems(){
    val numbers = mutableListOf(1, 2, 3,3,3, 4)
    println(numbers)
    numbers.retainAll { it >= 3 }
    println(numbers)
    numbers.clear()
    println(numbers)

    val numbersSet = mutableSetOf("one", "one", "one", "two", "three", "four")
    numbersSet.removeAll(setOf("one", "two"))
    println(numbersSet)
}

fun testMinusAssign(){
    val numbers = mutableListOf("one", "two", "three", "three", "four")
    numbers -= "three"
    println(numbers)
    numbers -= listOf("four", "five")
//numbers -= listOf("four")    // 与上述相同
    println(numbers)
}