package lession8

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main(args: Array<String>) {
    ChannelsDemo().testChannel()
}

/**
 * 当管道没有值的时候，管道会刮起，等待值的进入
 * 此处需要重点关注的是通道的模型，通道是一个 生产者消费者模型，我们可以在几个点做测试
 * 1.当我们注释掉repeat中的 channel.receive() 即关闭接受者，我们会发现程序无法结束
 * 2.当我们把注释去掉，我们发现消费会触发生产，并且生产比消耗快点，同时，生产之后不会产过消耗。
 * 所以生产两个 消耗两个。
 * 3.我们在 生产处 加入 delay(100) 后再观察日志 发现生产在消耗前面，生产之后才能消耗，且一一对应。
 * 4.我们在 消耗处 加入 delay(100) 后再观察日志 发现消耗在生产后面，生产被消耗后才能再生产，且一一对应。
 * 5.这里面我们要注意一个非常重要的参数 capacity 容纳的能力
 * 这个参数可以理解为　channel 的缓存能力 他的默认值是0 即我们写一个空的构造方法时
 * val channel = Channel<Int>() 此时管道的能力是1 从我们调用发送的地方输入的日志就可以看出来
 * 那么我们改变参数 val channel = Channel<Int>(2) 此时发送了3个数 1 4 9
 * 这证明了管道的能力是我们传参后+1 并且我们要注意的是 我们的程序并没有完成运行！
 * 只要管道处于输出，而未接收的状态，就会刮起当前的协程。
 *
 * 这个能力是十分重要的。
 *
 * */
class ChannelsDemo {
    fun testChannel() = runBlocking<Unit> {
        val channel = Channel<Int>(2)
        launch {
            for (x in 1..10) {
                channel.send((x * x).also { println("product ${x * x} !")} )
            }
        }

        println("channel = ${channel}")
        // here we print five received integers:
        repeat(10) {
            delay(100)
            println("Consume ${channel.receive()}")
        }

        println("Done!")
    }
}

/**
 * 与队列不同, 通道可以关闭, 以指示没有更多的元素。在接收端, 可以使用 for 循环从通道接收元素。
 * */
fun testClosingAndIterationChannels() = runBlocking {
    val channel = Channel<Int>()
    launch {
        for (x in 1..5) channel.send(x * x)
        channel.close() // 我们结束 sending
    }
    // 打印通道中的值，直到通道关闭
    for (x in channel) println(x)
    println("Done!")
}

/**
 * 此方法帮我们验证 当我们调用了通道关闭后
 * 当前通道的打开状体和最后一个元素的关系
 * 在管道完成最后一个元素的发送后， 我们关闭了管道 管道处于关闭状态，但是我们可以取得最后一个元素。
 * */
fun testClosingAndIterationChannels2() = runBlocking {
    val channel = Channel<Int>()
    launch {
        for (x in 1..5) {
            channel.send(x * x)
        }
        println("Before Close => isClosedForSend = ${channel.isClosedForSend}")
        channel.close() // 我们结束 sending
        println("After Close => isClosedForSend = ${channel.isClosedForSend}")
    }
    // 打印通道中的值，直到通道关闭
    for (x in channel) {
        println("${x} => isClosedForReceive = ${channel.isClosedForReceive}")
    }
    println("Done!  => isClosedForReceive = ${channel.isClosedForReceive}")
}