package lession2.PartA_Grammar.extends


open class Rectangle() {

    var borderColor: Int = 0

    open fun draw() {
        println("draw Rectangle")
    }
}

class FilledRectangle : Rectangle() {

    override fun draw() {
        val filler = Filler()
        filler.drawAndFill()
    }

    inner class Filler {
        fun fill() {
            println("Filling")
        }

        fun drawAndFill() {
            /**
             * 不写  super@FilledRectangle. 会发生递归报错！ 死递归了
             * */
            super@FilledRectangle.draw() // 调用 Rectangle 的 draw() 实现
            fill()
            println("Drawn a filled rectangle with color ${super@FilledRectangle.borderColor}") // 使用 Rectangle 所实现的 borderColor 的 get()
        }
    }
}

/**
 * 在 Kotlin 中，实现继承由下述规则规定：如果一个类从它的直接超类继承相同成员的多个实现，
 * 它必须覆盖这个成员并提供其自己的实现（也许用继承来的其中之一）。 为了表示采用从哪个超类型继承的实现，我们使用由尖括号中超类型名限定的 super，如 super<Base>：
 * */

open class Rectangle1 {
    open fun draw() { /* …… */ }
}

interface Polygon {
    fun draw() { /* …… */ } // 接口成员默认就是“open”的
}

class Square() : Rectangle1(), Polygon {
    // 编译器要求覆盖 draw()：
    override fun draw() {
        super<Rectangle1>.draw() // 调用 Rectangle.draw()
        super<Polygon>.draw() // 调用 Polygon.draw()
    }
}