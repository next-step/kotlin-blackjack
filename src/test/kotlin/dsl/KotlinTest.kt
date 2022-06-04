package dsl

fun main() {
//    "Kotlin".lastChar()
    println(1 to "1")
}

infix fun Any.to(other: Any) = Pair(this, other)

fun String.lastChar(): Char {
    return this[this.length - 1]
}

class KotlinTest {
}
