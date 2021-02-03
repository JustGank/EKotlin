package lession6.A_Grammar

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * 我们通常取消协同执行的原因给协程的执行时间设定一个执行时间上限。
 * 我们也可以使用 withTimeout 函数来给一个协程任务的执行设定最大执行时间，超出这个时间，就直接终止掉。
 *
 * */

fun main() {
    try {
        testTimeouts()
    } catch (e: CancellationException) {
        println("I am timed out!")
    }

}

/**
 * 程序会在超时的时候 停止运行并报错
 * 由 withTimeout 抛出的 TimeoutException 是 CancellationException 的一个子类。
 * 此时程序异常结束， 在timeout之外的 println("time out!") 不会输出执行
 * 如何解决：加try catch 这样也比较友好
 try {
 testTimeouts()
} catch (e: CancellationException) {
println("I am timed out!")
}
 *
 * */
fun testTimeouts() = runBlocking {
    withTimeout(3000L) {
        repeat(100) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }

    println("time out!")
}