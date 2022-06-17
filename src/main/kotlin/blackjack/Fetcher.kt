package blackjack

fun interface Fetcher<A, B> {
    fun fetch(args: A): B
}
