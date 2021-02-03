package lession3.PartA_Grammar.SExpand

/**
 * 扩展不能真正的修改他们所扩展的类。通过定义一个扩展，你并没有在一个类中插入新成员， 仅仅是可以通过该类型的变量用点表达式去调用这个新函数。
 * */
fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // “this”对应该列表
    this[index1] = this[index2]
    this[index2] = tmp
}

fun main(){

    val list = mutableListOf(1, 2, 3)
    list.swap(0, 2) // “swap()”内部的“this”会保存“list”的值

}