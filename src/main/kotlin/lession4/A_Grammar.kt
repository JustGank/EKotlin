package lession4
fun main(){

    println("Hello Kotlin")

    hello("b",12)

}

fun hello(a:String="A",b:Int=10){
    println(a)
}


class A_Grammar {

 var c = arrayOf(1,2,3);

}

inline class innTest(val s:A_Grammar);

class IntTransformer: (Int) -> Int {
    override operator fun invoke(x: Int): Int = TODO()
}
