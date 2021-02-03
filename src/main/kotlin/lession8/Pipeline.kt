package lession8

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlin.coroutines.CoroutineContext


fun main() = runBlocking {

    testPipeline()

}

/**
 * 数据生成管道
 * 这里我们需要重点关注的是 produce 的返回值！
 * public fun <E> CoroutineScope.produce(...): ReceiveChannel<E>
 * produce的返回都是一个接收通道 而square的接收对象也是一个 ReceiveChannel<E>
 * 这才是管道可以串联的原因，即从管道的入口，不断经过串联起来的管道。
 * numbersFrom 可以视为管道的入口
 * */
fun CoroutineScope.numbersFrom(start: Int) = produce<Int>() {
    println("numbersFrom start")
    var x = start
    while (true) {
        x++
        send(x.also { println("numbersFrom send $x") }) // 开启了一个无限的整数流

    }
}

/**
 * 入参为 ReceiveChannel<Int> 证明这是一段管道，或者是管道的出口
 * */
fun CoroutineScope.square(numbers: ReceiveChannel<Int>, s: String) = produce<Int>() {
    for (x in numbers) {
        send((x).also { println(" $s receive x=$x") })
    }
}

/**
 * 首先 我门先简单的看下 一个管道入口 接一个管道的情况
 * 管道的内部实现是channal 所以 capacity = 2 参数就是 赋值给channal的
 *
 * 1.场景一不开启接收者 并且不在最外层添加延迟delay(100) 此时日志仅输入 testPipeline i = ，此时可以证明 若我们不开启接收器 整个管道是不工作的
 * 2.场景二不开启接受者 在最外层添加delay(100)
 *   但是此时我将生产函数的能力值设置0 我们发现 numbersFrom 管道依然 发送了两个数 square接收了一个数
 *   在此场景下 我们将 square 函数的能力提升至2 此时我们发现 numbersFrom 管道 发送了4个数 square接收了一个数接收了3个数
 *   基于以上我们可以得出，每个管道段都可以看成一个 大小为一的缓存池 ，这个池指的是 接收和发送共享的
 *   例如我们在上面的条件喜 继续将 numbersFrom 设置 capacity = 2 会发现 numbersFrom一共发了6个数 squares1 收了三个数
 *   另外我们可以看出 numbersFrom 会一次将下层需要的数发满，然后再将自己的缓存池占满
 *
 *   基于以上我们 我们再加一个管道
 *
 * 3.场景三 numbersFrom capacity 默认为0 此时我们再接一个管道 依然不开启最后的接收
 *   此时我们发现管道依然会逐层向下住满
 *   先发送2 一直传递到 squares2 接收到2 squares2 满
 *   然后发送3 一直传递到 squares1 接收到3 squares1 满
 *   最后发送4 numbersFrom 满
 *
 *   基于以上得出结论 每一段管道都是可以设置缓存池大小的，实际的缓存池大小是 1+capacity
 *   即使我们最后不接收，管道也会先将能填满的管道段填满
 *
 * 5.场景四我们打开接收 此时只打开 squares1 作为接收
 *   这个时候日志就稍微有写复杂了 我们需要明确几个点
 *   5.1管道的发送一定是从 入口先开始的
 *   5.2管道的第一段接收完成后 他的发送会触发 管道入口的 发送
 *   5.3此时管道入口会发出可占满当前所有子管道的元素 比如现在是两段管道 就会发2个数
 *   5.4由于一次将当前的管道都占满了 所以子管道 需要接收第一个数 在排除第一个数 还有第二个数未接收
 *   5.5再接收一个数排空自己的缓存后 才会触发 管道入口在此发送新的元素， 此时由回到了5.2步 然后循环这个过程
 *
 * 6.场景五 为了验证这个 结论我们再增加一段管道 squares2
 *   这个我们也可以看到在完成2的接收后 numbersFrom连续的发了 345 当前是三段管道
 *   然后需要等子管道消耗调管道内的缓存后 有发送了6 注意此时 45 还在子管道内缓存
 *   后面又发了78 将能占用的缓存沾满
 *
 *
 * 当然我们也可以 不用这么详细的了解 发送和每段管道的关系 即 一个队列是以什么顺序在管道内运行的
 * 我们只需要明确 从管道入口进入的元素，到管道出口，在管道的每个段都要经过一遍，这和生活中的管道很像
 * 几个水管接成一根水管后，只要水管不漏，水从一端进入后需要经过管道的每一段才能流出。
 *
 *
 * 最后我们要注意，记得关闭管道，要不会造成程序一直运行
 * */
fun testPipeline() = runBlocking {
    val numbers = numbersFrom(1) // produces integers from 1 and on
    val squares1 = square(numbers, "squares1") // squares integers
    val squares2 = square(squares1, " squares2")
    //for (i in 1..6) println(squares.receive())
    for (i in 1..6) {
        println("   testPipeline receive" + squares2.receive())

    }
    delay(100)
    println("Done!")
    squares2.cancel()
    squares1.cancel()
    numbers.cancel()
}

