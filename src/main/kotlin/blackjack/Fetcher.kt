package blackjack

fun interface Fetcher<A, B> {
    operator fun invoke(args: A): B
}
