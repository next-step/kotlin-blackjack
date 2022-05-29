package blackjack.domain

@JvmInline
value class Hand(private val cards: List<Card>) {

    val count: Int
        get() = cards.size
}
