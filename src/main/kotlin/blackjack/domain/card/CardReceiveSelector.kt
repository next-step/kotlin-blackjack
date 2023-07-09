package blackjack.domain.card

fun interface CardReceiveSelector {
    fun cardReceiveNotWant(name: String): Boolean
}
