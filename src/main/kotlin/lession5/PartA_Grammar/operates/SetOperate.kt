package lession5.PartA_Grammar.operates

fun main() {
    val numbers = setOf("one", "two", "three")

    println(numbers union setOf("four", "five"))
    println(setOf("four", "five") union numbers)

    println(numbers intersect setOf("two", "one"))
    println(numbers subtract setOf("three", "four"))
    println(numbers subtract setOf("four", "three"))
    println(numbers subtract setOf("four", "three"))

    println(setOf("four", "three") subtract numbers)
}