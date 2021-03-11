package lession7

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun main() {
    producePrimesSequences()
}

/**
 * 首先我们确实完成了一个无穷质数序列
 * 而且我们所控制的出口看出 最后一个出口 并不影响输入 即 我们在 producePrimesSequences 把while循环的条件设置为
 * 100 后 numbersProducer 实际生成了 上千个数！
 * 所以仅当最后一段接收到了 才算完成了一次接收.
 *
 * 第二个事情是我们 由于没有关闭 所以程序一直处于运行中
 * 当然我们可以使用 一个集合标记所有的 管道段然后进行关闭
 * */
fun producePrimesSequences() = runBlocking {
    var producerJob = numbersProducer(2)
    var i = 0
    var list = ArrayList<ReceiveChannel<Int>>()
    list.add(producerJob)
    while (i++ < 100) {
        val prime = producerJob.receive()
        println("producePrimesSequences $i  ${prime} \t")
        producerJob = filterPrimes(producerJob, prime)
        list.add(producerJob)
    }
    for (producerJob in list) {
        producerJob.cancel()
    }
}

fun CoroutineScope.numbersProducer(start: Int) = produce<Int>() {
    var n = start
    while (true) {
        //println("numbersProducer $n")
        send(n++)
    }// infinite stream of integers from start
}

fun CoroutineScope.filterPrimes(numbers: ReceiveChannel<Int>, prime: Int) = produce<Int>() {
    for (x in numbers) if (x % prime != 0) send(x)
}