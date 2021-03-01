package lession8.grammar

fun main(){

    demo2()


}

fun printAll(collection: Collection<String>){
    for (s in collection){
        print("$s ")
    }

}

fun demo1(){
    val stringList= listOf<String>("One","Two","One")

    var stringSet= setOf("One","Two","Three")

    printAll(stringList)
    println()
    printAll(stringSet)
}

fun demo2() {
    val words = "A long time ago in a galaxy far far away".split(" ")
    val shortWords = mutableListOf<String>()
    words.getShortWordsTo(shortWords, 3)
    println(shortWords)
}
fun List<String>.getShortWordsTo(shortWords: MutableList<String>, maxLength: Int) {
    this.filterTo(shortWords) { it.length <= maxLength }
    // throwing away the articles
    val articles = setOf("a", "A", "an", "An", "the", "The")
    shortWords -= articles
}