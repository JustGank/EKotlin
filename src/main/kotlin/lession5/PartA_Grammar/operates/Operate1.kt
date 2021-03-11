package lession5.PartA_Grammar.operates

fun main() {
    testToOperate()
}

fun testToOperate() {
    val numbers = listOf("one", "two", "three", "four")
    val filterResults = mutableListOf<String>()  // 目标对象
    numbers.filterTo(filterResults) { it.length > 3 }
    numbers.filterIndexedTo(filterResults) { index, item ->
        index == 3.also {
            print("$item ")
        }
    }
    println(filterResults) // 包含两个操作的结果
}

/**
 * sort() 就地对可变集合进行排序，因此其状态发生了变化
 * sorted() 创建一个新集合，该集合包含按排序顺序相同的元素。
 * */
fun testSortAndSorted(){
    val numbers = mutableListOf("one", "two", "three", "four")
    val sortedNumbers = numbers.sorted()
    println(numbers == sortedNumbers)  // false
    numbers.sort()
    println(numbers == sortedNumbers)  // true
}