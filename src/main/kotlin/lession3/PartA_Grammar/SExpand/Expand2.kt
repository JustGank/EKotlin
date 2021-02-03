package lession3.PartA_Grammar.SExpand

open class Shape

class Rectangle: Shape()

fun Shape.getName() = "Shape"

fun Rectangle.getName() = "Rectangle"

fun printClassName(s: Shape) {
    println(s.getName())
}

/**
 * 扩展是静态解析的
 * 扩展不能真正的修改他们所扩展的类。通过定义一个扩展，你并没有在一个类中插入新成员，
 * 仅仅是可以通过该类型的变量用点表达式去调用这个新函数。
 * 我们想强调的是扩展函数是静态分发的，即他们不是根据接收者类型的虚方法。
 * 这意味着调用的扩展函数是由函数调用所在的表达式的类型来决定的， 而不是由表达式运行时求值结果决定的。
 * */

fun main(){
    printClassName(Rectangle())
}
