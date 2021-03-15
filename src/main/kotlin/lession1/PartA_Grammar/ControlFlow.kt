package lession1.PartA_Grammar

fun main() {

}

fun testIf() {
    val a = 10
    val b = 20
    // 传统用法
    var max1: Int = a
    if (a < b) max1 = b

    //With else
    var max2: Int
    if (a > b) {
        max2 = a
    } else {
        max2 = b
    }

    //作为表达式
    val max3 = if (a > b) a else b

    val max4 = if (a > b) {
        print("Choose a")
        a
    } else {
        print("Choose b")
        b
    }
}

fun testWhen2(x: Int) {
    when (x) {
        1 -> print("x == 1")
        2 -> print("x == 2")
        else -> { // 注意这个块
            print("x is neither 1 nor 2")
        }
    }

    when (x) {
        0, 1 -> print("x == 0 or x == 1")
        else -> print("otherwise")
    }

    var s: String = "1"
    when (x) {
        parseInt(s) -> print("s encodes x")
        else -> print("s does not encode x")
    }

    val validNumbers = 30..40
    when (x) {
        in 1..10 -> print("x is in the range")
        in validNumbers -> print("x is valid")
        !in 10..20 -> print("x is outside the range")
        else -> print("none of the above")
    }

}

fun hasPrefix(x: Any) = when (x) {
    is String -> x.startsWith("prefix")
    else -> false
}

fun testFor() {

    for (i in 1..3) {
        println(i)
    }
    for (i in 6 downTo 0 step 2) {
        println(i)
    }

    var collection = listOf<Int>(1, 2, 3, 4)

    for (item in collection) print(item)

    for ((index, value) in collection.withIndex()) {
        println("the element at $index is $value")
    }

}

fun testWhile2() {
    var x: Int = 10
    while (x > 0) {
        x--
    }

    do {
        val y = null
    } while (y != null) // y 在此处可见
}
