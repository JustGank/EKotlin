package lession3.PartA_Grammar.failed

class GetSetTest {

    var params1: String="params1"
        get() = field + "1"

        set(value: String) {
            value + field
        }



}

fun main(){
    println("GetSetTest().params1="+GetSetTest().params1)
}