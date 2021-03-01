package lession2.PartA_Grammar.constructor

class Constructor1

class Constructor2()

class Constructor3() {}

/**
 * 在 Kotlin 中的一个类可以有一个主构造函数以及一个或多个次构造函数。主构造函数是类头的一部分：它跟在类名（与可选的类型参数）后。
 * */
/**
 * 主构造函数 无省略模式
 * 与普通属性一样，主构造函数中声明的属性可以是可变的（var）或只读的（val）。
 * */
class Constructor4 constructor(var params1: String, val params2: Float, params3: Int) {}

/**
 * 主构造函数 省略模式
 * */
class Constructor5(params1: String) {}

/**
 * 主构造函数不能包含任何的代码。初始化的代码可以放到以 init 关键字作为前缀的初始化块（initializer blocks）中。
 * */

class Constructor6(params1: String) {

    val firstProperty = "First property: $params1".also(::println)

    init {
        println("First initializer block that prints ${params1}")
    }

    val secondProperty = "Second property: ${params1.length}".also(::println)

    init {
        println("Second initializer block that prints ${params1.length}")
    }
}

class Constructor7(params1: String) {


    var object1 = Constructor7("I am Constructor7 1")

    init {
        var object2 = Constructor7("I am Constructor 7 2")
    }

}

/**
 * 如果构造函数有注解或可见性修饰符，这个 constructor 关键字是必需的，并且这些修饰符在它前面
 * */
class Constructor8  public @OptIn constructor(name: String) { /*……*/ }