package lession1.PartA_Grammar

import java.io.File
import java.math.BigDecimal

//可以在Build类中查看他的Java实现
data class Customer(val name: String, val email: String)

fun main() {
    calcTaxes()
}

/**
 * 函数的默认参数
 * 不要运行此方法
 * */
fun foo(a: Int = 0, b: String = "") {
    foo(a = 123)
    foo(b = "abc")
    throw IllegalCallerException()
}

/**
 * 过滤 list
 * */
fun testFilter() {
    val list = listOf(1, 2, 3, 4, 5)
    var positives = list.filter { x -> x > 0 }
    println(positives)
    positives = list.filter { it < 0 }
    println(positives)
}

/**
 * 检测元素是否存在于集合中
 * */
fun testIn() {
    val emailsList = emptyList<String>()
    if ("john@example.com" in emailsList) {
    }

    if ("jane@example.com" !in emailsList) {
    }
}

/**
 * 类型判断
 * */
fun testWhen(x: Any) {
    when (x) {
        is Int -> println("$x is int")
        is String -> println("$x is String")
        else -> println("$x is not int or String")
    }
}

/**
 * 遍历 map/pair型list
 * */
fun testPairMap() {
    val map = mapOf('a' to 100, 'b' to 200, 'c' to 300)
    for ((k, v) in map) {
        println("$k -> $v")
    }
}

/**
 * 使用区间
 * */
fun testInArray() {
    for (i in 1..10) {
        println(i)
    }  // 闭区间：包含 100
    for (i in 1 until 10) {
        println(i)
    } // 半开区间：不包含 100
    for (x in 2..10 step 2) {
        println(x)
    }
    for (x in 10 downTo 1) {
        println(x)
    }
    if (x in 1..10) {
        println(x)
    }
}

/**
 * 只读 list
 * */
fun testReadOnlyList() {
    val list = listOf("a", "b", "c")
}

/**
 * 只读 map
 * */
fun testReadOnlyMap() {
    val map = mapOf("a" to 1, "b" to 2, "c" to 3)
}

/**
 * 访问 map
 * */
fun testVisitMap() {
    val map = mutableMapOf("a" to 1, "b" to 2, "c" to 3)
    map["key"] = 123
    println(map["key"])
}

/**
 * 延迟属性
 */
fun testByLazy() {
    val p: String by lazy {
        println("Init p")
        "Hello"
    }
}

/**
 * 扩展函数
 * */
fun String.spaceToCamelCase() {}

fun testExpandableFun() {
    "Convert this to camelcase".spaceToCamelCase()
}

/**
 * 创建单例
 * */
object Resource {
    val name = "Name"
}

fun testSingle() {
    println(Resource.name)
}

/**
 * If not null 缩写
 * */
fun testIfNotNull() {
    val files = File("Test").listFiles()
    //会输出null不会崩溃
    println(files?.size)
    //断言会空针异常
    println(files!!.size)
}

/**
 * If not null and else 缩写
 * */
fun testIfNotNullElse() {
    val files = File("Test").listFiles()
    //会输出Empty
    println(files?.size ?: "empty")
}

/**
 * if null 执行一个语句
 * */
fun testIfNull() {
    val values = emptyMap<String, String>()
    val email = values["email"] ?: throw IllegalStateException("Email is missing!")
}

/**
 * 在可能会空的集合中取第一元素
 * */
fun testIfNullTakeFirst() {
    val emails = emptyList<String>()
    val mainEmail = emails.firstOrNull() ?: ""
}

/**
 * if not null 执行代码
 */
fun testIfNotNullLet() {
    val value = "Hello World"

    value?.let {
        println(it)
    }
}

/**
 * 映射可空值（如果非空的话）
 * */
fun testLetNull() {
    val value = "Hello World!"

    val mapped = value?.let { null } ?: "let null"

    println(mapped)
}

/**
 * 返回 when 表达式
 */
fun testTransform(color: String): Int {
    return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }
}

/**
 * “try/catch”表达式
 * */
fun testTryCatch() {
    val result = try {
        1000
    } catch (e: ArithmeticException) {
        throw IllegalStateException(e)
    }
}

/**
 * “if”表达式
 * */
fun testIf(param: Int) {
    val result = if (param == 1) {
        "one"
    } else if (param == 2) {
        "two"
    } else {
        "three"
    }
    println(result)
}

/**
 * 返回类型为 Unit 的方法的 Builder 风格用法
 * */
fun arrayOfMinusOnes(size: Int): IntArray {
    return IntArray(size).apply { fill(-1) }.also {
        for (item in it) {
            print(item)
        }
    }
}

/**
 * 单表达式函数
 * theAnswer 与 theAnswer2 等价
 * */
fun theAnswer() = 42

fun theAnswer2(): Int {
    return 42
}

fun transform(color: String): Int = when (color) {
    "Red" -> 0
    "Green" -> 1
    "Blue" -> 2
    else -> throw IllegalArgumentException("Invalid color param value")
}

/**
 * 对一个对象实例调用多个方法 （with）
 * */
class Turtle {
    fun penDown() = "penDown"
    fun penUp() = "penUp"
    fun turn(degrees: Double) = "turn"
    fun forward(pixels: Double) = "forward"
}

fun testWith() {
    val myTurtle = Turtle()
    with(myTurtle) { // 画一个 100 像素的正方形
        println(penDown())
        for (i in 1..4) {
            println(forward(100.0))
            println(turn(90.0))
        }
        println(penUp())
    }
}

/**
 * 配置对象的属性（apply）
 * */

fun testApply() {
    class Rectangle {
        var length: Int = 0
        var breadth: Int = 0
        var color: Int = 0
    }

    val myRectangle = Rectangle().apply {
        length = 4
        breadth = 5
        color = 0xFAFAFA
    }
}

/***
 * 对于需要泛型信息的泛型函数的适宜形式
 */
//inline fun <reified T: Any> Gson.fromJson(json: JsonElement): T = this.fromJson(json, T::class.java)

/**
 * 使用可空布尔
 * */
fun testNullableBoolean() {
    val b: Boolean? = null
    if (b == true) {
        println("b is not null is $b")
    } else {
        println("`b` 是 false 或者 null")
    }
}

/**
 * 交换两个变量
 * */
fun testAlso(){
    var a = 1
    var b = 2
    a = b.also { b = a }
    println("Now a=$a b=$b")
}

/**
 * TODO()：将代码标记为不完整
 * */
fun calcTaxes(): BigDecimal = TODO("Waiting for feedback from accounting")