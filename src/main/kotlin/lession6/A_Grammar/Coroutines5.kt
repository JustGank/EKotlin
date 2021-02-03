package lession6.A_Grammar

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Unconfined
import java.util.concurrent.ForkJoinPool

fun main() {
    testDispatchersAndThreads3()
}

fun testDispatchersAndThreads() = runBlocking {

    val jobs = arrayListOf<Job>()

    jobs += launch { // 未作限制 -- 将会在 main thread 中执行x
        println("Empty  : I'm working in thread ${Thread.currentThread()}")
    }

    jobs += launch(Unconfined) {
        // 未作限制 -- 将会在 main thread 中执行
        println("Unconfined: I'm working in thread ${Thread.currentThread()}")
    }

    jobs += launch(Dispatchers.Default) {
        // 未作限制 -- 将会在 DefaultDispatcher-worker-1 thread 中执行
        println("Dispatchers.Default: I'm working in thread ${Thread.currentThread()}")
    }

    jobs += launch(coroutineContext) {
        // 父协程的上下文 ： runBlocking coroutine
        println("coroutineContext: I'm working in thread ${Thread.currentThread()}")
    }

    jobs += launch(newSingleThreadContext("MyOwnThread")) {
        // 将会在这个协程自己的新线程中执行
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread()}")
    }

    jobs.forEach { it.join() }
}

/**
 * 协程现在的规则已经 去掉按照 协程父子上下文 来判断是否释放子协程
 * */
fun testChildrenCoroutine() = runBlocking<Unit> {
    val request = launch {
        log("ContextA1: newSingleThreadContext ")
        val job1 = launch(newSingleThreadContext("MyOwnThread")) {
            println("job1: 独立的协程上下文!")
            delay(1000)
            println("job1:会受到request.cancel()的影响")
        }
        // 继承父上下文：request的context
        val job2 = launch {
            log("ContextA2: ${coroutineContext}")
            println("job2: 是request coroutine的子协程")
            delay(1000)
            println("job2: 当request.cancel()，job2也会被取消")
        }
        job1.join()
        job2.join()
    }
    delay(500)
    request.cancel()
    delay(1000)
    println("main: Who has survived request cancellation?")
}

fun log(msg: String) = println("${Thread.currentThread()} $msg")

fun testDispatchersAndThreads2() = runBlocking {
    val request = launch {
        // 孵化了两个子作业, 其中一个通过 GlobalScope 启动
        GlobalScope.launch {
            println("job1: I run in GlobalScope and execute independently!")
            delay(1000)
            println("job1: I am not affected by cancellation of the request")
        }
        // 另一个则承袭了父协程的上下文
        launch {
            delay(100)
            println("job2: I am a child of the request coroutine")
            delay(1000)
            println("job2: I will not execute this line if my parent request is cancelled")
        }
    }
    delay(500)
    request.cancel() // 取消请求（request）的执行
    delay(1000) // 延迟一秒钟来看看发生了什么
    println("main: Who has survived request cancellation?")
}

/**
 * 一个父协程总是等待所有的子协程执行结束。父协程并不显式的跟踪所有子协程的启动，并且不必使用 Job.join 在最后的时候等待它们：
 * */
fun testDispatchersAndThreads3()= runBlocking() {
    // 启动一个协程来处理某种传入请求（request）
    val request = launch {
        repeat(3) { i -> // 启动少量的子作业
            launch  {
                delay((i + 1) * 200L) // 延迟 200 毫秒、400 毫秒、600 毫秒的时间
                println("Coroutine $i is done")
            }
        }
        println("request: I'm done and I don't explicitly join my children that are still active")
    }
    request.join() // 等待请求的完成，包括其所有子协程
    println("Now processing of the request is complete")
}
