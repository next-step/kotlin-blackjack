package blackjack.domain.dsl

fun interface Builder<T> {
    fun build(): T
}
