package lession3.PartA_Grammar.SAM

//对于函数式接口，可以通过 lambda 表达式实现 SAM 转换，从而使代码更简洁、更有可读性。
fun interface IntPredicate {
    fun accept(i: Int): Boolean
}


fun main() {

    val isEven1 = object : IntPredicate {
        override fun accept(i: Int): Boolean {
            return i % 2 == 0
        }
    }

    // 通过 lambda 表达式创建一个实例
    val isEven2 = IntPredicate { it % 2 == 0 }

    println("Is 7 even? - ${isEven1.accept(7)}")

    println("Is 7 even? - ${isEven2.accept(7)}")
}