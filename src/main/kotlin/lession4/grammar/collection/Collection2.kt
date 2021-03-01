package lession4.grammar.collection

fun main() {
    testMutableMap()
}

/**
 * List<T> 以指定的顺序存储元素，并提供使用索引访问元素的方法。索引从 0 开始 – 第一个元素的索引 – 直到 最后一个元素的索引 即 (list.size - 1)。
 * */
fun colletion2_demo1() {
    val numbers = listOf("one", "two", "three", "four")
    println("Number of elements: ${numbers.size}")
    println("Third element: ${numbers.get(2)}")
    println("Fourth element: ${numbers[3]}")
    println("Index of element \"two\" ${numbers.indexOf("two")}")
}

/**
 * List 元素（包括空值）可以重复：List 可以包含任意数量的相同对象或单个对象的出现。 如果两个 List 在相同的位置具有相同大小和相同结构的元素，则认为它们是相等的。
 * 预计运行结果：
 * true
 * false
 * */
fun collection2_demo2() {
    val bob = Person("Bob", 31)
    val people = listOf(Person("Adam", 20), bob, bob)
    val people2 = listOf(Person("Adam", 20), Person("Bob", 31), bob)
    println(people == people2)
    bob.age = 32
    println(people == people2)

    val person1 = Person("Name", 30)
    val person2 = Person("Name", 30)
    println("person1==person2 is ${person1 == person2}")
    println("person1.equals(person2) is ${person1.equals(person2)}")
    print("person1===person2 is ${person1 === person2}")

}

/**
 *  这里面我们要关注几个要点，目前在网上还没看到此部分说明
 *  1.如果我们声明的不是数据类而只是普通的类那么运行结果为：
 *  false false
 *  2.如果希望运行结果一直 我们可以通过重写 equals 方法 来符合预期运行结果：
 *  true false
 *  3.最后我们声明Person为数据类的时候运行结果为：
 *  true false
 *  4.我们再引申一个测试 一个类当我们声明他为数据类的时候 看看 == equals === 的判断结果
 *  person1==person2 is true
 *  person1.equals(person2) is true
 *  person1===person2 is false
 *
 *  当我们去掉数据类的限制，将Person变成普通类
 * person1==person2 is false
 * person1.equals(person2) is false
 * person1===person2 is false
 *
 * 从结果看出，数据类和普通的类 相比明显 是数据类重写了equals方法和hashCode方法，实际上还重写了copy方法
 * 这个我们可以看数据类的编译结果进行验证。
 * 数据类重新了类的：HashCode equals copy toString 等方法
 * 类不会有以上重写
 * */
data
class Person constructor(var name: String, var age: Int)
//{
//    override fun equals(other: Any?): Boolean {
//        return  name.equals((other as Person).name)&&age==other.age
//    }
//}

fun testMutableList() {
    val numbers = mutableListOf(1, 2, 3, 4)
    numbers.add(5)
    numbers.removeAt(1)
    numbers[0] = 0
    numbers.shuffle()
    println(numbers)
}

fun testSet(){
    val numbers = setOf(1, 2, 3, 4)
    println("Number of elements: ${numbers.size}")
    if (numbers.contains(1)) println("1 is in the set")

    val numbersBackwards = setOf(4, 3, 2, 1)
    println("The sets are equal: ${numbers == numbersBackwards}")
}

fun testMutableSet(){
    val numbers = setOf(1, 2, 3, 4)  // LinkedHashSet is the default implementation
    val numbersBackwards = setOf(4, 3, 2, 1)

    println(numbers.first() == numbersBackwards.first())
    println(numbers.first() == numbersBackwards.last())
}

fun testMap(){
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)

    println("All keys: ${numbersMap.keys}")
    println("All values: ${numbersMap.values}")
    if ("key2" in numbersMap) println("Value by key \"key2\": ${numbersMap["key2"]}")
    if (1 in numbersMap.values) println("The value 1 is in the map")
    if (numbersMap.containsValue(1)) println("The value 1 is in the map") // 同上
}

/**
 * 无论键值对的顺序如何，包含相同键值对的两个 Map 是相等的。
 * */
fun testMap2(){
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)
    val anotherMap = mapOf("key2" to 2, "key1" to 1, "key4" to 1, "key3" to 3)

    println("The maps are equal: ${numbersMap == anotherMap}")
}

fun testMutableMap(){
    val numbersMap = mutableMapOf("one" to 1, "two" to 2)
    numbersMap.put("three", 3)
    numbersMap["one"] = 11

    println(numbersMap)
}