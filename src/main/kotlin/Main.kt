package com.mato.app

import com.sun.source.tree.YieldTree
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.broadcast
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.*
import java.lang.ArithmeticException

@OptIn(DelicateCoroutinesApi::class)
suspend fun main() {
    GlobalScope.launch {
        createFlow().collect()
    }.join()
}

@OptIn(DelicateCoroutinesApi::class, ObsoleteCoroutinesApi::class)
suspend fun test() {
    val channel = Channel<Int>().broadcast(3)
    val producer = GlobalScope.launch {
        repeat(5) {
            delay(1000)
            channel.send(it)
        }
        channel.close()
    }
    List(3) { index ->
        GlobalScope.launch {
            val receiveChannel = channel.openSubscription()
            for (i in receiveChannel) {
                println("receive $i in $index")
            }
        }
    }.joinAll()
}

@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
suspend fun test2() {
    val channel = GlobalScope.produce(Dispatchers.Unconfined) {
        println("A")
        send(1)
        println("B")
        send(2)
        println("C")
        send(3)
    }

    for (item in channel) {
        println(item)
    }
}

suspend fun test3() {
    val sequence = sequence {
        println("A")
        yield(1)
        println("B")
        yield(2)
        println("C")
        yield(3)
    }

    println("before sequence")
    for (item in sequence) {
        println(item)
    }
}

@OptIn(DelicateCoroutinesApi::class)
suspend fun test4() {
    val intFlow = flow<Int> {
        (1..3).forEach {
            emit(it)
            delay(1000)
        }
    }.flowOn(Dispatchers.IO)

    GlobalScope.launch(Dispatchers.IO) {
        intFlow.collect {
            println(it)
        }

        intFlow.collect {
            println(it)
        }
    }.join()

    flow {
        emit(1)
        throw ArithmeticException("Div 0")
    }.catch {
        println(it.message)
    }.onCompletion {
        println("onCompletion")
    }.collect {
        println(it)
    }
}

fun createFlow() = flow {
    (1..3).forEach {
        emit(it)
        delay(1000)
    }
}.onEach {
    println("onEach $it")
}