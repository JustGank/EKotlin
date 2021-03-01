package lession2.PartA_Grammar.impl

interface InterfaceA {


    val prop: Int // 抽象的

    val propertyWithImplementation: String
        get() = "foo"

    fun hello(){
        println("InterfaceA Hello")
    }


}