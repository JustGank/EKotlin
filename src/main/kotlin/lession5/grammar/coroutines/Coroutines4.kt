package lession6.A_Grammar


import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


fun main() = runBlocking<Unit> {
    testAsync()
}


suspend fun doJob1(): Int {
    println("Doing Job1 ...")
    delay(1000L) // 此处模拟我们的工作代码
    println("Job1 Done")
    return 10
}

suspend fun doJob2(): Int {
    println("Doing Job2 ...")
    delay(1000L) // 此处模拟我们的工作代码
    println("Job2 Done")
    return 20
}

fun testSequential() = runBlocking<Unit> {
    val time = measureTimeMillis {
        val one = doJob1()
        val two = doJob2()
        println("[testSequential] 最终结果： ${one + two}")
    }
    println("[testSequential] Completed in $time ms")
}

/**
 * 从概念上讲, async跟launch类似, 它启动一个协程, 它与其他协程并发地执行。
 * 从语法上来说，我们可以使用launch 代替 async ,不过此时返回值会报错！
 *  不同之处在于, launch返回一个任务Job对象, 不带任何结果值；
 *  而async返回一个延迟任务对象Deferred，一种轻量级的非阻塞性future, 它表示后面会提供结果。
 *  我们使用Deferred调用 await() 函数来获得其最终结果。另外，延迟任务Deferred也是Job类型,
 *  它继承自Job，所以它也有isActive、isCompleted属性，也有join()、cancel()函数，因此我们也可以在需要时取消它。
 * */
fun testAsync() = runBlocking<Unit> {
    val time = measureTimeMillis {
        val one = async { doJob1() }
        val two = async { doJob2() }
        println("最终结果： ${one.await() + two.await()}")
        println("measureTimeMillis end !")
    }
    println("Completed in $time ms")
}