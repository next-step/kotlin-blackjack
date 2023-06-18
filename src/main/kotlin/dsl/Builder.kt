package dsl

fun interface Builder<T> {
    fun build(): T
}
