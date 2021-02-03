package lession3.PartA_Grammar.SExpand

/**
 * 注意：由于扩展没有实际的将成员插入类中，因此对扩展属性来说幕后字段是无效的。
 * 这就是为什么扩展属性不能有初始化器。他们的行为只能由显式提供的 getters/setters 定义。
 *
 * 这代表我们新增的属性必定是和已有属性相关联的！
 * */
var <T> MutableList<T>.lastData: T
    //获取集合中最后一个对象
    get() = this[this.size - 1]
    //设置集合中最后一个对象的值
    set(value) {
        this[this.size - 1] = value
    }



fun main(){

}