package lession8.PartA_Grammar

 fun main(){

 }

/**
 * 不需要运行仅展示空集合方法
 */
fun displayEmpty(){
    val empty = emptyList<String>()
}

fun displayInitMethod(){
    val doubled = List(3, { it * 2 })  // 如果你想操作这个集合，应使用 MutableList
    println(doubled)
}

/**
 * 通过Copy 可以改变元素的集合类型
 * */
fun displayCopy(){
    val sourceList = mutableListOf(1, 2, 3)
    val copyList = sourceList.toMutableList()
    val readOnlyCopyList = sourceList.toList()
    sourceList.add(4)
    println("Copy size: ${copyList.size}")

//readOnlyCopyList.add(4)             // 编译异常
    println("Read-only copy size: ${readOnlyCopyList.size}")
}

fun displayFilter(){
    val numbers = listOf("one", "two", "three", "four")
    val longerThan3 = numbers.filter { it.length > 3 }
    println(longerThan3)
}

fun displayMap(){
    val numbers = setOf(1, 2, 3)
    println(numbers.map { it * 3 })
    println(numbers.mapIndexed { idx, value -> value * idx })
}

/**
 * 关联生成 Map
 * */
fun displayAssociate(){
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.associateWith { it.length })
}