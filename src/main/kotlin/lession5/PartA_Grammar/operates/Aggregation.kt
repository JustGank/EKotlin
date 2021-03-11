package lession5.PartA_Grammar.operates

fun main() {
    testRecudeFoldIndexed()
}

fun testAggregation1() {
    val numbers = listOf(6, 6, 42, 42, 10, 4)

    println("Count: ${numbers.count()}")
    println("Max: ${numbers.maxOrNull()}")
    println("Min: ${numbers.minOrNull()}")
    println("Average: ${numbers.average()}")
    println("Sum: ${numbers.sum()}")
}

fun testAggregation2() {
    val numbers = listOf(5, 42, 10, 4)
    val min3Remainder = numbers.minByOrNull { it % 3 }
    println(min3Remainder)

    val strings = listOf("one", "two", "three", "four")
    val longestString = strings.maxWithOrNull(compareBy { it.length })
    println(longestString)
}

fun testAggregation3() {
    val strings = listOf("one", "two", "three", "four")
    println(strings.sumBy { it.length * 2 })
    println(strings.sumByDouble { it.length.toDouble() / 2 })
}

fun testFoldAndReduce() {
    val numbers = listOf(5, 2, 10, 4)

    val sum = numbers.reduce { sum, element -> sum + element }
    println(sum)
    val sumDoubled = numbers.fold(0) { sum, element -> sum + element * 2 }
    println(sumDoubled)
}

fun testReduceRight() {
    val numbers = listOf(5, 2, 10, 4)
    val sumDoubledRight = numbers.foldRight(0) { element, sum -> sum + element * 2 }
    println(sumDoubledRight)

    println("reduce:" + numbers.reduce { sum, element -> sum - element })
    println("reduceRight:" + numbers.reduceRight { element, sum -> sum - element })

}

fun testRecudeFoldIndexed() {

    val numbers = listOf(5, 2, 10, 4)
    val sumEven = numbers.foldIndexed(0) { idx, sum, element -> if (idx % 2 == 0) sum + element else sum }
    println(sumEven)

    val sumEvenRight = numbers.foldRightIndexed(0) { idx, element, sum -> if (idx % 2 == 0) sum + element else sum }
    println(sumEvenRight)
}