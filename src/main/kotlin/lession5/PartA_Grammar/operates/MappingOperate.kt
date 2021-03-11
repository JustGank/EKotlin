package lession5.PartA_Grammar.operates

fun main(){
    testJoinToString()
}

fun testMap(){
    val numbers = setOf(1, 2, 3)
    println("map"+numbers.map { if ( it == 2) null else it * 3 })
    println("mapIndexed"+numbers.mapIndexed() { idx, value -> if (idx == 0) null else value * idx })
    println()
    println("mapNotNull"+numbers.mapNotNull { if ( it == 2) null else it * 3 })
    println("mapIndexedNotNull"+numbers.mapIndexedNotNull { idx, value -> if (idx == 0) null else value * idx })
}

fun testMap1(){
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    println(numbersMap.mapKeys { it.key.length })
    println(numbersMap.mapValues { "asd"+it.value + it.key.length })
}

fun testZip(){
    val colors = listOf("red", "brown", "grey")
    val animals = listOf("fox", "bear", "wolf")
    println(colors zip animals)

    val twoAnimals = listOf("fox", "bear")
    println(colors.zip(twoAnimals))
}

fun testZip2(){
    val colors = listOf("red", "brown", "grey")
    val animals = listOf("fox", "bear", "wolf")

    println(colors.zip(animals) { color, animal -> "The ${animal.capitalize()} is $color"})
}

fun testUnzip(){
    val numberPairs = listOf("one" to 1, "two" to 2, "three" to 3, "four" to 4)
    println(numberPairs.unzip())
}

fun testAssociate(){
    val numbers = listOf("one", "two", "three", "four")
    numbers.associateWith {  }
    println(numbers.associateBy { it.first().toUpperCase() })
    println(numbers.associateBy(keySelector = { it.first().toUpperCase() }, valueTransform = { it.length }))
}

fun testAssociate2(){
    data class FullName (val firstName: String, val lastName: String)

    fun parseFullName(fullName: String): FullName {
        val nameParts = fullName.split(" ")
        if (nameParts.size == 2) {
            return FullName(nameParts[0], nameParts[1])
        } else throw Exception("Wrong name format")
    }

    val names = listOf("Alice Adams", "Brian Brown", "Clara Campbell")
    println(names.associate { name -> parseFullName(name).let { it.lastName to it.firstName } })
}

fun testFlatten(){
    val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
    println(numberSets.flatten())
}

fun testJoinToString(){
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.joinToString(separator = " | ", prefix = "start ", postfix = " end"))
}