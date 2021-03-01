package lession4.grammar.operates

fun main() {

    testShuffled()


}

class Version(val major: Int, val minor: Int) : Comparable<Version> {
    override fun compareTo(other: Version): Int {
        if (this.major != other.major) {
            return this.major - other.major
        } else if (this.minor != other.minor) {
            return this.minor - other.minor
        } else return 0
    }
}

fun testComparable() {
    println(Version(1, 2) > Version(1, 3))
    println(Version(2, 0) > Version(1, 5))

}

fun testCustomComparable() {
    val intComparable = Comparator { int1: Int, int2: Int -> int1 - int2 }
    println(listOf(4, 5, 2, 1).sortedWith(intComparable))

    val lengthComparator = Comparator { str1: String, str2: String -> str1.length - str2.length }
    println(listOf("aaa", "bb", "c").sortedWith(lengthComparator))
}

fun testCompareBy() {
    println(listOf("aaa", "bb", "c").sortedWith(compareBy { it.length }))
}

fun testNormalSorted() {
    val numbers = listOf("one", "two", "three", "four")
    println("Sorted ascending: ${numbers.sorted()}")
    println("Sorted descending: ${numbers.sortedDescending()}")
}

fun testcumtomSortedBy() {
    val numbers = listOf("one", "two", "three", "four")
    val sortedNumbers = numbers.sortedBy { it.length }
    println("Sorted by length ascending: $sortedNumbers")
    val sortedByLast = numbers.sortedByDescending { it.last() }
    println("Sorted by the last letter descending: $sortedByLast")
}

fun testSortedWith() {
    val numbers = listOf("one", "two", "three", "four")
    println("Sorted by length ascending: ${numbers.sortedWith(compareBy { it.length })}")
}

fun testReversed() {
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.reversed())
}

fun testAsReversed() {
    val numbers = listOf("one", "two", "three", "four")
    val reversedNumbers = numbers.asReversed()
    println(reversedNumbers)
}

/**
 * 如果是可变的集合 在改版集合元素后 会影响 通过反转产生的集合！
 * */
fun testMutableList() {
    val numbers = mutableListOf("one", "two", "three", "four")
    val reversedNumbers = numbers.asReversed()
    println(reversedNumbers)
    numbers.add("five")
    println(reversedNumbers)
}

fun testShuffled() {
    val numbers = listOf("one", "two", "three", "four")
    for (i in 1..100) {
        println(numbers.shuffled())
    }
}