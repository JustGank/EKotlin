package lession4.grammar.sequence

fun main(){

    displayIterable()
    println()
    displaySequence()

}

fun displayIterable(){
    val words = "The quick brown fox jumps over the lazy dog".split(" ")
    val lengthsList = words.filter { println("filter: $it"); it.length > 3 }
        .map { println("length: ${it.length}"); it.length }
        .take(4)

    println("Lengths of first 4 words longer than 3 chars:")
    println(lengthsList)
}

fun displaySequence(){
    val words = "The quick brown fox jumps over the lazy dog".split(" ")
// 将列表转换为序列
    val wordsSequence = words.asSequence()

    val lengthsSequence = wordsSequence.filter { println("filter: $it"); it.length > 3 }
        .map { println("length: ${it.length}"); it.length }
        .take(4)

    println("Lengths of first 4 words longer than 3 chars")
// 末端操作：以列表形式获取结果。
    println(lengthsSequence.toList())

}

