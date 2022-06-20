package blackjack

fun interface Printer<A> {
    operator fun invoke(args: A)
}
