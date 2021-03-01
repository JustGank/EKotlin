package lession2.PartA_Grammar.constructor

/**
 * 类也可以声明前缀有 constructor的次构造函数
 * */
class Constructor9{
    var children: MutableList<Constructor9> = mutableListOf()
    constructor(parent: Constructor9) {
        parent.children.add(this)
    }
}

/**
 * 次构造函数 重载
 * */
class Constructor10{
    constructor(param1:String){
        //会报错，因为只识别声明的构造
        //var object1=Constructor10()
    }

    constructor(param1:String,params2:String)
}

class Constructor11{
    init {
        println("Constructor11 init run!")
    }

    constructor(params1:String){
        println("Constructor11 constructor run params1=$params1!")
    }
}

/**
 * 如果一个非抽象类没有声明任何（主或次）构造函数，它会有一个生成的不带参数的主构造函数。
 * 构造函数的可见性是 public。如果你不希望你的类有一个公有构造函数，你需要声明一个带有非默认可见性的空的主构造函数
 * */

class Constructor12 private constructor () { /*……*/ }