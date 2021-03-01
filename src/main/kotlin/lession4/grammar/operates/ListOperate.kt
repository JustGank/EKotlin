package lession4.grammar.operates

import kotlin.math.sign

fun main() {
    testListSort()
}

fun testGet() {
    val numbers = listOf(1, 2, 3, 4)
    println(numbers.get(0))
    println(numbers[0])
//numbers.get(5)                         // exception!
    println(numbers.getOrNull(5))             // null
    println(numbers.getOrElse(5, { it }))        // 5
}

fun testGetPart() {
    val numbers = (0..13).toList()
    println(numbers)
    println(numbers.subList(3, 6))
}

fun testGetPosition() {
    val numbers = listOf(1, 2, 3, 4, 2, 5)
    println(numbers.indexOf(2))
    println(numbers.lastIndexOf(2))
}

fun testIndexOfFirst() {
    val numbers = mutableListOf(1, 2, 3, 4)
    println(numbers.indexOfFirst { it > 2 })
    println(numbers.indexOfLast { it % 2 == 1 })
}


fun testBinarySearch() {
    val numbers = mutableListOf("one", "two", "three", "four")
    numbers.sort()
    println(numbers)
    println(numbers.binarySearch("two"))  // 3
    println(numbers.binarySearch("z")) // -5
    println(numbers.binarySearch("two", 0, 2))  // -3
}

fun testComparatorSearch() {
    data class Product(val name: String, val price: Double)

    val productList = listOf(
        Product("WebStorm", 49.0),
        Product("AppCode", 99.0),
        Product("DotTrace", 129.0),
        Product("ReSharper", 149.0)
    )

    println(productList.binarySearch(Product("AppCode", 99.0),
        compareBy<Product> { it.price }.thenBy { it.name })
    )
}

fun testbinarySearch2() {
    val colors = listOf( "Red","Blue", "green", "ORANGE", "yellow")
    val nColors=colors.sortedWith(String.CASE_INSENSITIVE_ORDER)
    println(nColors)
    println(nColors.binarySearch("RED", String.CASE_INSENSITIVE_ORDER)) // 3
}

fun testComparison(){
    data class Product(val name: String, val price: Double)
    //将我们的数据对象映射到整形上
    fun priceComparison(product: Product, price: Double) = sign(product.price - price).toInt()

    val productList = listOf(
        Product("WebStorm", 49.0),
        Product("AppCode", 99.0),
        Product("DotTrace", 129.0),
        Product("ReSharper", 149.0))
    //这样我们就可以类似模糊搜索了 被搜索的字段需要有序！
    println(productList.binarySearch { priceComparison(it, 99.0) })
}

fun testListAdd(){
    val numbers = mutableListOf("one", "five", "six")
    numbers.add(1, "two")
    numbers.addAll(2, listOf("three", "four"))
    println(numbers)
}

fun testListUpdata(){
    val numbers = mutableListOf("one", "five", "three")
    numbers[1] =  "two"
    println(numbers)
}

fun testListFill(){
    val numbers = mutableListOf(1, 2, 3, 4)
    numbers.fill(3)
    println(numbers)
}

fun testListDeleteItem(){
    val numbers = mutableListOf(1, 2, 3, 4, 3)
    numbers.removeAt(1)
    println(numbers)
}

fun testListRemoveFirstOrLast(){
    val numbers = mutableListOf(1, 2, 3, 4, 3)
    numbers.removeFirst()
    numbers.removeLast()
    println(numbers)

    val empty = mutableListOf<Int>()
// empty.removeFirst() // NoSuchElementException: List is empty.
    empty.removeFirstOrNull() //null
}

fun testListSort(){
    val numbers = mutableListOf("one", "two", "three", "four")

    numbers.sort()
    println("Sort into ascending: $numbers")
    numbers.sortDescending()
    println("Sort into descending: $numbers")

    numbers.sortBy { it.length }
    println("Sort into ascending by length: $numbers")
    numbers.sortByDescending { it.last() }
    println("Sort into descending by the last letter: $numbers")

    numbers.sortWith(compareBy<String> { it.length }.thenBy { it })
    println("Sort by Comparator: $numbers")

    numbers.shuffle()
    println("Shuffle: $numbers")

    numbers.reverse()
    println("Reverse: $numbers")
}