package lession3.PartA_Grammar.SExpand

/**
 * 如果一个类定义有一个成员函数与一个扩展函数，而这两个函数又有相同的接收者类型、 相同的名字，并且都适用给定的参数，这种情况总是取成员函数。
 * */
class Example {
    var name:String="Hello KT"

    fun printFunctionType() { println("Class method") }
}

fun Example.printFunctionType() { println("Extension function ${this.name}") }

fun Example.printFunctionType(i: Int) { println("Extension function") }

fun main(){
    Example().printFunctionType()

    Example().printFunctionType(1)
}


fun Any?.toString(): String {
    if (this == null) return "null"
    // 空检测之后，“this”会自动转换为非空类型，所以下面的 toString()
    // 解析为 Any 类的成员函数
    return toString()
}
