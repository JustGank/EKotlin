package lession1.PartA_Grammar


fun main() {
    testTrimMargin()

}

/**
 * 数字
 * Byte	8
 * Short	16
 * Int	32
 * Long	64
 * */
fun testIntNumber() {
    val one = 1 // Int
    val threeBillion = 3000000000 // Long
    val oneLong = 1L // Long
    val oneByte: Byte = 1
    println(
        """one class is ${one.javaClass}
       |threeBillion  class is ${threeBillion.javaClass}
       |oneLong  class is ${oneLong.javaClass}
       |oneByte  class is ${oneByte.javaClass}
    """.trimMargin()
    )
}

fun testDoubleNumber() {
    val pi = 3.14 // Double
    val e = 2.7182818284 // Double
    val eFloat = 2.7182818284f // Float，实际值为 2.7182817
    println(
        """pi class is ${pi.javaClass}
       |e  class is ${e.javaClass}
       |eFloat  class is ${eFloat.javaClass}
     """.trimMargin()
    )
}

fun testPrintDouble() {

    fun printDouble(d: Double) {
        print(d)
    }

    val i = 1
    val d = 1.1
    val f = 1.1f

    printDouble(d)
//    printDouble(i) // 错误：类型不匹配
//    printDouble(f) // 错误：类型不匹配

}

fun testNumberWith_() {
    val oneMillion = 1_000_000
    val creditCardNumber = 1234_5678_9012_3456L
    val socialSecurityNumber = 999_99_9999L
    val hexBytes = 0xFF_EC_DE_5E
    val bytes = 0b11010010_01101001_10010100_10010010
}

fun testBox() {
    for (i in 120..130) {
        val boxedA: Int? = i
        val anotherBoxedA: Int? = i
        println("i =$i boxedA === anotherBoxedA is ${boxedA === anotherBoxedA}   boxedA == anotherBoxedA s ${boxedA == anotherBoxedA}") // true
    }
}

/**
 * 显示转换
 * */
fun testVisibleChange() {
    // 假想的代码，实际上并不能编译：
    val a: Int? = 1 // 一个装箱的 Int (java.lang.Integer)
//    val b: Long? = a // 隐式转换产生一个装箱的 Long (java.lang.Long)
//    print(b == a) // 惊！这将输出“false”鉴于 Long 的 equals() 会检测另一个是否也为 Long

    val b: Byte = 1 // OK, 字面值是静态检测的
    //   val i: Int = b // 错误
    val i: Int = b.toInt() // OK：显式拓宽
    print(i)

    val l = 1L + 3 // Long + Int => Long
    println(l.javaClass)
}

/**
 * 整数除法
 * */
fun testDivision() {
    val x1 = 5 / 2
//println(x == 2.5) // ERROR: Operator '==' cannot be applied to 'Int' and 'Double'
    println(x1 == 2)

    val x2 = 5L / 2
    println(x2 == 2L)

    val x3 = 5 / 2.toDouble()
    println(x3 == 2.5)

}

/**
 * 位运算
 * shl(bits) – 有符号左移
 * shr(bits) – 有符号右移
 * ushr(bits) – 无符号右移
 * and(bits) – 位与
 * or(bits) – 位或
 * xor(bits) – 位异或
 * inv() – 位非
 */
fun testBit() {
    val x = (1 shl 2) and 0x000FF000
    println("x is $x")
}

fun testDecimalDigitValue() {
    fun decimalDigitValue(c: Char): Int {
        if (c !in '0'..'9')
            throw IllegalArgumentException("Out of range")
        return c.toInt() - '0'.toInt() // 显式转换为数字
    }
    println("decimalDigitValue 1= ${decimalDigitValue('1')}")
    println("decimalDigitValue d= ${decimalDigitValue('d')}")
}

/**
 * 数组
 * */
fun testArray() {
    val asc = Array(5) { i -> (i * i).toString() }
    asc.forEach { println(it) }

    val x: IntArray = intArrayOf(1, 2, 3)
    x[0] = x[1] + x[2]
    println({ "x[0] is ${x[0]}" })

    // 大小为 5、值为 [0, 0, 0, 0, 0] 的整型数组
    val arr1 = IntArray(5)

    // 例如：用常量初始化数组中的值
    // 大小为 5、值为 [42, 42, 42, 42, 42] 的整型数组
    val arr2 = IntArray(5) { 42 }

    // 例如：使用 lambda 表达式初始化数组中的值
    // 大小为 5、值为 [0, 1, 2, 3, 4] 的整型数组（值初始化为其索引值）
    var arr3 = IntArray(5) { it * 1 }

}

/**
 * 无符号整型
 * */
fun testUnsignInt() {
    val a = 1UL // ULong，即使未提供预期类型并且常量适于 UInt
    val b: UByte = 1u // UByte，已提供预期类型
    val s: UShort = 1u // UShort，已提供预期类型
    val l: ULong = 1u  // ULong，已提供预期类型

    val a1 = 42u // UInt：未提供预期类型，常量适于 UInt
    val a2 = 0xFFFF_FFFF_FFFFu // ULong：未提供预期类型，常量不适于 UInt
}

/**
 * 字符串
 * */
fun testDisplayStrig() {
    val s1 = "abc" + 1
    println(s1 + "def")

    val s2 = "Hello, world!\n"
}

fun testTrimMargin() {

    //从 """ 声明开始 后面的内容就作为输出内容!
    val text1 = """
    for (c in "foo")
        print(c)
"""

    val text2 = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()

    println("text1 is $text1")
    println("text2 is $text2")
}


