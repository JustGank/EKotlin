package lession3.PartA_Grammar.extends

/**
 * 从 Any 隐式继承
 * Any 有三个方法：equals()、 hashCode() 与 toString()。因此，为所有 Kotlin 类都定义了这些方法。
 * 默认情况下，Kotlin 类是最终（final）的：它们不能被继承。 要使一个类可继承，请用 open 关键字标记它。
 * */
class Extends1 {
}


open class Extends2(params1: String) {}

/**
 * 由于 Extends1 不是Open的所以不能继承,Extends2可以被继承
 *
 * 如果派生类有一个主构造函数，其基类可以（并且必须） 用派生类主构造函数的参数就地初始化。
 * */
class Extends3(params1: String, params2: String) : Extends2(params2) {}

/**
 * 如果派生类没有主构造函数，那么每个次构造函数必须使用 super 关键字初始化其基类型，
 * 或委托给另一个构造函数做到这一点。 注意，在这种情况下，不同的次构造函数可以调用基类型的不同的构造函数：
 * */

open class Extends4 {

    val params1 = 1

    open val params2 =2

    constructor(params1: String)

    constructor(params1: String, params2: String)

    fun hello(params1: String) {
        println("Extends4 hello run -> $params1")
    }

    open fun hello2(params1: String) {
        println("Extends4 hello run -> $params1")
    }
}

/**
 * 含有主构造函数的类 其主构造可以在子类声明时初始化，也可以在 内部初始化
 *
 * open class Extends5 : Extends4
 *
 * 使用父类的方法时，请使用 super()
 * */
//open class Extends5 : Extends2 {}

open class Extends5 : Extends4 {
    //此处params 可以删除也不会报错。 前面的方法同样可以是多个参数形式。
    constructor(params1: String) : super(params1, "")

    /**
     * 由于hello1并不是open 方法，所以不能重写
     * hello2方法为open 方法，可以 override 重写
     * 标记为 override 的成员本身是开放的，也就是说，它可以在子类中覆盖。如果你想禁止再次覆盖，使用 final 关键字：
     * */
    final override fun hello2(params1: String) {
        super.hello2(params1)
        println("Extends5 hello run -> $params1")
    }

    /**
     * 覆盖属性
     * */
    //override val params1 = 2;
    override  val  params2=200

}





