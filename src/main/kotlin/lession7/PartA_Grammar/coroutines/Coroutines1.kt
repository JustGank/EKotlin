package lession6.PartA_Grammar

import kotlinx.coroutines.*


fun main()= runBlocking{

   test3()

}

/**
 * 测试用例一
 * 场景一：无任何延迟
 * 结论： 线程结束，协程并没有执行完毕。线程并没有等到协程执行完毕才结束。
 *
 * 场景二：开启线程睡2秒，线程休眠时间大于协程的休眠时间。
 * 结论：线程的休眠并没有阻塞协程，Hello和world输出时间大概相差一秒。
 * 由于协程的等待时间小于线程的等待时间，所以协程完成了运行
 *
 * 场景三：协程等待两秒，此处的主要区别是在没有 runBlocking包裹的方法内是不能使用 delay的。
 * 结论：同二
 * */
suspend fun test1() {
   GlobalScope.launch {
      delay(1000)
      println(System.currentTimeMillis().toString()+" World!")
   }
   println(System.currentTimeMillis().toString()+" Hello!")

   /**
    * 1611648096959 Hello!
    * 1611648097971 World!
    * 1611648098968 test finish!
    * */
//   Thread.sleep(2000L)
//   println(System.currentTimeMillis().toString()+" test finish!")

   /**
    * 1611648175434 Hello!
    * 1611648176443 World!
    * 1611648177448 test finish!
    * */
   runBlocking {
      delay(2000)
   }
   println(System.currentTimeMillis().toString()+" test finish!")

}

/**
 * 此方法测试主线线程结束时，线程内启动的协程状态
 * 由输入的日志可以看出，随着线程的结束，协程并没有完成运行。
 * */
suspend fun test2(){
   // Start a coroutine

   val c1 = GlobalScope.launch {
      println("C1 Thread: ${Thread.currentThread()}")
      println("C1 Start")
      delay(3000L)
      println("C1 World! 1")
   }
   val c2 = GlobalScope.launch {
      println("C2 Thread: ${Thread.currentThread()}")
      println("C2 Start")
      delay(5000L)
      println("C2 World! 2")
   }
   println("Main Thread: ${Thread.currentThread()}")
   println("Hello,")
   println("Hi,")
   println("c1 is active: ${c1.isActive}  ${c1.isCompleted}")
   println("c2 is active: ${c2.isActive}  ${c2.isCompleted}")
}

suspend fun test3(){
   val c1 = GlobalScope.launch {
      println("C1 Thread: ${Thread.currentThread()}")
      println("C1 Start")
      delay(3000L)
      println("C1 World! 1")
   }
   val c2 = GlobalScope.launch{
      println("C2 Thread: ${Thread.currentThread()}")
      println("C2 Start")
      delay(2000L)
      println("C2 World! 2")
   }
   println("Main Thread: ${Thread.currentThread()}")
   println("Hello,")
   println("c1 is active: ${c1.isActive}  isCompleted: ${c1.isCompleted}")
   println("c2 is active: ${c2.isActive}  isCompleted: ${c2.isCompleted}")
   c1.join() // the main thread will wait until child coroutine completes
   println("Hi,")
   println("c1 is active: ${c1.isActive}  isCompleted: ${c1.isCompleted}")
   println("c2 is active: ${c2.isActive}  isCompleted: ${c2.isCompleted}")
   c2.join() // the main thread will wait until child coroutine completes
   println("c1 is active: ${c1.isActive}  isCompleted: ${c1.isCompleted}")
   println("c2 is active: ${c2.isActive}  isCompleted: ${c2.isCompleted}")
}