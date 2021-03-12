package lession1.PartA_Grammar

import java.lang.Exception

/**
 * 程序入口点
 * */
fun main() {
    testCollection2();
}

/**
 * 函数
 * */
fun sum1(a: Int, b: Int): Int {
    return a + b
}

fun sum2(a: Int, b: Int) = a + b

fun printSum1(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

fun printSum2(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}

/**
 * 变量
 * */
fun testValAndVar() {
    val a: Int = 1  // 立即赋值
    val b = 2   // 自动推断出 `Int` 类型
    val c: Int  // 如果没有初始值类型不能省略
    c = 3       // 明确赋值

    var x = 5 // 自动推断出 `Int` 类型
    x += 1

}

val PI = 3.14
var x = 0

fun incrementX() {
    x += 1
}

/**
 * 注释
 * */
// 这是一个行注释

/* 这是一个多行的
   块注释。 */


/* 注释从这里开始
/* 包含嵌套的注释 */
并且在这里结束。 */

/**
 * 字符串模板
 * */
fun testString() {
    var a = 1
// 模板中的简单名称：
    val s1 = "a is $a"

    a = 2
// 模板中的任意表达式：
    val s2 = "${s1.replace("is", "was")}, but now is $a"
}

/**
 * 条件表达式
 * */
fun maxOf1(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

fun maxOf2(a: Int, b: Int) = if (a > b) a else b


/**
 * 类型检测与自动类型转换
 * */
//为了展示语法 补全了parseInt
fun parseInt(str: String): Int? {
    try {
        return str.toInt()
    } catch (e: Exception) {
        return null;
    }
}

fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    // 直接使用 `x * y` 会导致编译错误，因为它们可能为 null
    if (x != null && y != null) {
        // 在空检测后，x 与 y 会自动转换为非空值（non-nullable）
        println(x * y)
    } else {
        println("'$arg1' or '$arg2' is not a number")
    }

    if (x == null) {
        println("Wrong number format in arg1: '$arg1'")
        return
    }
    if (y == null) {
        println("Wrong number format in arg2: '$arg2'")
        return
    }

    // 在空检测后，x 与 y 会自动转换为非空值
    println(x * y)
}


fun getStringLength1(obj: Any): Int? {
    if (obj is String) {
        // `obj` 在该条件分支内自动转换成 `String`
        return obj.length
    }

    // 在离开类型检测分支后，`obj` 仍然是 `Any` 类型
    return null
}

fun getStringLength2(obj: Any): Int? {
    if (obj !is String) return null

    // `obj` 在这一分支自动转换为 `String`
    return obj.length
}

/**
 * for 循环
 * */
fun testFor1() {
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }
}

fun testFor2() {
    val items = listOf("apple", "banana", "kiwifruit")
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
}

/**
 * while 循环
 * */
fun testWhile() {
    val items = listOf("apple", "banana", "kiwifruit")
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }
}

/**
 * when 表达式
 * */
fun testWhen() {
    fun describe(obj: Any): String =
        when (obj) {
            1 -> "One"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a string"
            else -> "Unknown"
        }

    println(describe(1))
    println(describe("Hello"))
    println(describe(1000L))
    println(describe(2))
    println(describe("other"))
}

/**
 * 使用区间（range）
 * */
fun testRange() {
    val x = 10
    val y = 9
    if (x in 1..y + 1) {
        println("fits in range")
    }
}

fun testRange2() {
    val list = listOf("a", "b", "c")

    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    if (list.size !in list.indices) {
        println("list size is out of valid list indices range, too")
    }
}

fun testRange3(){
    for (x in 1..10 step 2) {
        print(x)
    }
    println()
    for (x in 9 downTo 0 step 3) {
        print(x)
    }
}

/**
 * 集合
 * */
fun testCollection(){
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }
}

fun testCollection2(){
    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits
            //过滤已'a'开头的字符串
        .filter { it.startsWith("a") }
            //按Sting 实现的comparable进行排序
        .sortedBy { it }
            //转换 变成大写
        .map { it.toUpperCase() }
            //将结果依次输出
        .forEach { println(it) }
}


abstract class Shape(val sides: List<Double>) {
    val perimeter: Double get() = sides.sum()
    abstract fun calculateArea(): Double
}

interface RectangleProperties {
    val isSquare: Boolean
}

class Rectangle(
    var height: Double,
    var length: Double
) : Shape(listOf(height, length, height, length)), RectangleProperties {
    override val isSquare: Boolean get() = length == height
    override fun calculateArea(): Double = height * length
}

/**
 * 创建基本类及其实例
 * */
fun testParentClass(){
    val rectangle = Rectangle(5.0, 2.0)
    val triangle = Triangle(3.0, 4.0, 5.0)
    println("Area of rectangle is ${rectangle.calculateArea()}, its perimeter is ${rectangle.perimeter}")
    println("Area of triangle is ${triangle.calculateArea()}, its perimeter is ${triangle.perimeter}")
}

class Triangle(
    var sideA: Double,
    var sideB: Double,
    var sideC: Double
) : Shape(listOf(sideA, sideB, sideC)) {
    override fun calculateArea(): Double {
        val s = perimeter / 2
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC))
    }
}