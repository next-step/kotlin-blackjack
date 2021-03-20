package util

import java.util.Stack

fun <T : Any> List<T>.toStack(): Stack<T> {
    val stack = Stack<T>()
    stack.addAll(this)
    return stack
}
