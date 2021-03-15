package lession1.PartA_Grammar

fun main() {
    testContinue()
}

/**
 * Break 标签
 * */
fun testBreak() {
    loop@ for (i in 1..10) {
        for (j in 1..10) {
            println("i=$i j=$j")
            if (j == 2) {
                println("i==$i j==2 berak loop")
                break@loop
            }
        }
    }
    println("for end!")
}

/**
 * Continue  标签
 * */
fun testContinue() {

    loop@ for (i in 1..10) {
        for (j in 1..10) {
            println("i=$i j=$j")
            if (j == 2) {
                println("i==$i j==2 berak loop")
                continue@loop
            }
        }
    }
    println("for end!")

}

/**
 * Return 标签
 * */
fun testReturn1() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return // 非局部直接返回到 foo() 的调用者
        print(it)
    }
    println("this point is unreachable")
}

fun testReturn2() {
    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
        print(it)
    }
    print(" done with explicit label")
}

fun testReturn3() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return@forEach // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
        print(it)
    }
    print(" done with implicit label")
}

/**
 * 我们用一个匿名函数替代 lambda 表达式。 匿名函数内部的 return 语句将从该匿名函数自身返回
 * */
fun testReturn4() {
    listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
        if (value == 3) return  // 局部返回到匿名函数的调用者，即 forEach 循环
        print(value)
    })
    print(" done with anonymous function")
}