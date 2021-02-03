package lession3.PartA_Grammar.failed

 /**
  * Kotlin 类中的属性既可以用关键字 var 声明为可变的，也可以用关键字 val 声明为只读的。
  * */

/**
 * Getters 与 Setters
 *
 * var <propertyName>[: <PropertyType>] [= <property_initializer>]
 * [<getter>]
 * [<setter>]
 *
 * 如果你需要改变一个访问器的可见性或者对其注解，但是不需要改变默认的实现， 你可以定义访问器而不定义其实现:
 * */
class Address {


    var name: String = "Holmes, Sherlock"

    //由于Kotlin是严格判空的所以我们不能直接声明一个空对象
    lateinit var subject: String

    fun subjectIsNull(){

    }

    var street: String = "Baker"



    var isEmpty:Boolean =false

    //如果属性至少一个访问器使用默认实现，或者自定义访问器通过 field 引用幕后字段，
    // 将会为该属性生成一个幕后字段。
    //不要直接写 street=value 会造成递归错误！
    /**
    * In Kotlin, a field is only used when needed as part of a property to hold
    * its value in memory. Fields can not be declared directly.
    * 然而，当一个属性需要一个幕后字段时，Kotlin 会自动提供。
    * 这个幕后字段可以使用field标识符在访问器中引用：
    * */
    get() = field.equals("")

    set(value) {
        field =value
    }

    var city: String = "London"
    var state: String? = null
    var zip: String = "123456"

    var setterVisibility: String = "abc"
        private set // 此 setter 是私有的并且有默认实现

    var setterWithAnnotation: Any? = null
        @OptIn set // 用 Inject 注解此 setter


    //幕后属性
    //这是我们人为的考语法实现的！
    //如果你的需求不符合这套“隐式的幕后字段”方案，那么总可以使用 幕后属性（backing property）：
    private var _table: Map<String, Int>? = null
    public val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap() // 类型参数已推断出
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }



}



