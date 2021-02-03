package lession6.A_Grammar

import kotlinx.coroutines.*


/**
 * 在Java中有两类线程：用户线程 (User Thread)、守护线程 (Daemon Thread)。
 * 所谓守护线程，是指在程序运行的时候在后台提供一种通用服务的线程，比如垃圾回收线程就是一个很称职的守护者，
 * 并且这种线程并不属于程序中不可或缺的部分。因此，当所有的非守护线程结束时，程序也就终止了，同时会杀死进程中的所有守护线程。
 *
 * kotlin 协程的所有suspend 函数都是可以取消的。我们可以通过job的isActive状态来判断协程的状态，或者检查是否有抛出 CancellationException 时取消。
 *
 */

fun main() {

    finallyCancelDemo()


}

fun testCancellation() = runBlocking<Unit> {
    val job = launch {
        repeat(1000) { i ->
            println("I'm sleeping $i ... CurrentThread: ${Thread.currentThread()}")
            delay(500L)
        }
    }
    delay(1300L)
    println("CurrentThread: ${Thread.currentThread()}")
    println("Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    val b1 = job.cancel() // cancels the job
    println("job cancel: $b1")
    delay(1300L)
    println("Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    val b2 = job.cancel() // cancels the job, job already canceld, return false
    println("job cancel: $b2")
    println("main: Now I can quit.")
}

/**
 * 协程正工作在循环计算中，并且不检查协程当前的状态, 那么调用cancel来取消协程将无法停止协程的运行, 如下面的示例所示:
 *
 * 结论：我们可以看出，即使我们调用了cancel函数，当前的job状态isAlive是false了，但是协程的代码依然一直在运行，并没有停止。
 * */
fun testCooperativeCancellation1() = runBlocking<Unit> {
    val job = launch {
        var nextPrintTime = 0L
        var i = 0
        while (i < 20) { // computation loop
            val currentTime = System.currentTimeMillis()
            if (currentTime >= nextPrintTime) {
                println("I'm sleeping ${i++} ... CurrentThread: ${Thread.currentThread()}")
                nextPrintTime = currentTime + 500L
            }
        }
    }
    println("CurrentThread1: ${Thread.currentThread()}")
    delay(3000L)
    println("CurrentThread2: ${Thread.currentThread()}")
    println("Before cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    val b1 = job.cancel() // cancels the job
    println("job cancel1: $b1")
    println("After Cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    delay(30000L)
    val b2 = job.cancel() // cancels the job, job already canceld, return false
    println("job cancel2: $b2")
    println("main: Now I can quit.")
}

/**
 * 计算代码协程的有效取消
 * 1。显式检查取消状态isActive 失效
 * */

fun testCooperativeCancellation2() = runBlocking<Unit> {
    val job = launch {
        var nextPrintTime = 0L
        var i = 0
        while (i < 20) { // computation loop
            if (!isActive) {
                return@launch
            }
            val currentTime = System.currentTimeMillis()
            if (currentTime >= nextPrintTime) {
                println("I'm sleeping ${i++} ... CurrentThread: ${Thread.currentThread()}")
                nextPrintTime = currentTime + 500L
            }
        }
    }
    delay(3000L)
    println("CurrentThread: ${Thread.currentThread()}")
    println("Before cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    val b1 = job.cancel() // cancels the job
    println("job cancel1: $b1")
    println("After Cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    delay(3000L)
    val b2 = job.cancel() // cancels the job, job already canceld, return false
    println("job cancel2: $b2")
    println("main: Now I can quit.")
}

/**
 * 循环调用一个挂起函数yield()
 * 该方法实质上是通过job的isCompleted状态值来捕获CancellationException完成取消功能。
 * 如果我们想看看yield函数抛出的异常，我们可以加上try catch打印出日志：
 * try {
 * yield()
 * } catch (e: Exception) {
 * println("$i ${e.message}")
 * }
 * 尽量不要使用try catch
 * 可用生效
 * */
fun testCooperativeCancellation3() = runBlocking<Unit> {
    val job = launch {
        var nextPrintTime = 0L
        var i = 0
        while (i < 20) { // computation loop

                yield()

            val currentTime = System.currentTimeMillis()
            if (currentTime >= nextPrintTime) {
                println("I'm sleeping ${i++} ... CurrentThread: ${Thread.currentThread()}")
                nextPrintTime = currentTime + 500L
            }
        }
    }
    delay(3000L)
    println("CurrentThread: ${Thread.currentThread()}")
    println("Before cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    val b1 = job.cancel() // cancels the job
    println("job cancel1: $b1")
    println("After Cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    delay(3000L)
    val b2 = job.cancel() // cancels the job, job already canceld, return false
    println("job cancel2: $b2")
    println("main: Now I can quit.")
}

/**
 * 在finally中的协程代码
 * 当我们取消一个协程任务时，如果有try {...} finally {...}代码块，那么finally {…}中的代码会被正常执行完毕：
 * */
fun finallyCancelDemo() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            println("I'm running finally")
        }
    }
    delay(2000L)
    println("Before cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    job.cancel()
    println("After cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    delay(2000L)
    println("main: Now I can quit.")
}

/**
 * 但是，如果我们在finally{...}中放入挂起函数：
 * 运行上述代码，我们将会发现只输出了一句：I’m running finally。
 *
 * 因为主线程在挂起函数delay(1000L)以及后面的打印逻辑还没执行完，就已经结束退出。
 * */
fun finallyCancelDemo2() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            println("I'm running finally")
            delay(1000L)
            println("And I've delayed for 1 sec ?")
        }
    }
    delay(2000L)
    println("Before cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    job.cancel()
    println("After cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    delay(2000L)
    println("main: Now I can quit.")
}

/**
 * 本例中愿意为展示一个不可取消的finally但是目前已经失效 run 函数不接收
 * NonCancellable
 * */
fun testNonCancellable() = runBlocking {
    val job = launch{
        try {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            NonCancellable
            run   {
                println("I'm running finally")
                delay(1000L)
                println("And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(2000L)
    println("main: I'm tired of waiting!")
    job.cancel()
    delay(2000L)
    println("main: Now I can quit.")
}