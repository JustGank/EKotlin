package lession4.grammar.operates

fun main() {
    testGroupingBy()
}

fun testGroupBy() {
    val numbers = listOf("one", "two", "three", "four", "five")

    println(numbers.groupBy { it.first().toUpperCase() })
    println(numbers.groupBy(keySelector = { it.first() }, valueTransform = { it.toUpperCase() }))
}

fun testGroupingBy(){
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println("groupingBy eachCount"+numbers.groupingBy { it.first() }.eachCount())
}