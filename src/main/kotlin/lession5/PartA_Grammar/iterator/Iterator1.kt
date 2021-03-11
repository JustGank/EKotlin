package lession5.PartA_Grammar.iterator

fun main() {
    mutableListIterator()
}

/**
 * 常见的三种游标写法
 * */
fun normalIterator() {
    val numbers = listOf("one", "two", "three", "four")

    val numbersIterator = numbers.iterator()
    while (numbersIterator.hasNext()) {
        println(numbersIterator.next())
    }

    for (item in numbers) {
        println(item)
    }

    numbers.forEach {
        println(it)
    }

}

/**
 * 对于列表，有一个特殊的迭代器实现：
 * ListIterator 它支持列表双向迭代：正向与反向。
 * 反向迭代由 hasPrevious() 和 previous() 函数实现。
 * 此外， ListIterator 通过 nextIndex() 与 previousIndex() 函数提供有关元素索引的信息。
 */
fun listIterator(){
    val numbers = listOf("one", "two", "three", "four")
    val listIterator = numbers.listIterator()
    while (listIterator.hasNext()) listIterator.next()
    println("Iterating backwards:")
    while (listIterator.hasPrevious()) {
        print("Index: ${listIterator.previousIndex()}")
        println(", value: ${listIterator.previous()}")
    }
}

/**
 * 游标的第一次next指向第一个元素，所以移除的也是第一个元素。
 * */
fun mutableIterator(){
    val numbers = mutableListOf("one", "two", "three", "four")
    val mutableIterator = numbers.iterator()

    mutableIterator.next()
    mutableIterator.remove()
    println("After removal: $numbers")
}

fun mutableListIterator(){
    val numbers = mutableListOf("one", "four", "four")
    val mutableListIterator = numbers.listIterator()

    mutableListIterator.next()
    mutableListIterator.add("two")
    mutableListIterator.next()
    mutableListIterator.set("three")
     println(numbers)
}