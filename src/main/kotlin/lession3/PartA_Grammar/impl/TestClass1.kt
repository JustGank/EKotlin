package lession3.PartA_Grammar.impl

fun main(){
    TestClass1().hello()
}

class TestClass1 :InterfaceA,InterfaceB {
    //为初始化的字段 会提醒你重写进行初始化该值
    override val prop: Int
        get() = TODO("Not yet implemented")

    override fun hello() {
        super<InterfaceA>.hello()
        super<InterfaceB>.hello()
    }


}