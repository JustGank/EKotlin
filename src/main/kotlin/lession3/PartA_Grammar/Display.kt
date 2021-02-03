package lession3.PartA_Grammar
import lession3.PartA_Grammar.constructor.Constructor11
import lession3.PartA_Grammar.constructor.Constructor6
import lession3.PartA_Grammar.extends.Derived
import lession3.PartA_Grammar.extends.Extends1
import lession3.PartA_Grammar.extends.Extends5
import lession3.PartA_Grammar.extends.FilledRectangle
import lession3.PartA_Grammar.failed.Address

/**
 * 课程三 展示单元
 * 一、类可以包含 constructor
 * 1.构造函数与初始化块
 * 2.函数
 * 3.属性
 * 4.嵌套类与内部类
 * 5.对象声明
 * 此部分见代码 constructor分之
 *
 * 二、在 Kotlin 中所有类都有一个共同的超类 Any，这对于没有超类型声明的类是默认超类：
 *
 * 三、抽象类 类以及其中的某些成员可以声明为 abstract。
 *
 * 四、Kotlin 的接口可以既包含抽象方法的声明也包含实现。与抽象类不同的是，接口无法保存状态。它可以有属性但必须声明为抽象或提供访问器实现。
 * 一个接口可以从其他接口派生，从而既提供基类型成员的实现也声明新的函数与属性。很自然地，实现这样接口的类只需定义所缺少的实现：
 *
 * 五、只有一个抽象方法的接口称为函数式接口或 SAM（单一抽象方法）接口。函数式接口可以有多个非抽象成员，但只能有一个抽象成员。
 *
 * 六、可见性修饰符
 *
 * 七、扩展
 * Kotlin 能够扩展一个类的新功能而无需继承该类或者使用像装饰者这样的设计模式。
 * 这通过叫做 扩展 的特殊声明完成。
 * 例如，你可以为一个你不能修改的、来自第三方库中的类编写一个新的函数。
 * 这个新增的函数就像那个原始类本来就有的函数一样，可以用普通的方法调用。
 * 这种机制称为 扩展函数 。
 * 此外，也有 扩展属性 ， 允许你为一个已经存在的类添加新的属性。
 *
 *
 * */
fun main(){

  //  var object1= Constructor6("Hello KT")

  //var object2=Constructor11("Hello KT");

  //  println("在 Kotlin 中所有类都有一个共同的超类 Any ${Extends1() is Any}")

//    var object3= Extends5("Hello KT");
//    object3.hello2("ABC")

//    var object4= Derived("Params1","Params2")

//    var object5= FilledRectangle();
//    object5.draw()

 //   不错因为set 方法是私有的所以不能设置值
//   Address().setterVisibility="123";

}



class Display {



}