package lession3.PartA_Grammar.impl.visible

/**
 * 包
 * 如果你不指定任何可见性修饰符，默认为 public，这意味着你的声明将随处可见；
 * 如果你声明为 private，它只会在声明它的文件内可见；
 * 如果你声明为 internal，它会在相同模块内随处可见；
 * protected 不适用于顶层声明。
 *
 * 类和接口
 * private 意味着只在这个类内部（包含其所有成员）可见；
 * protected—— 和 private一样 + 在子类中可见。
 * internal —— 能见到类声明的 本模块内 的任何客户端都可见其 internal 成员；
 * public —— 能见到类声明的任何客户端都可见其 public 成员。
 *
 *
 * */

public fun hello(){
    println("File1 Hello")
}