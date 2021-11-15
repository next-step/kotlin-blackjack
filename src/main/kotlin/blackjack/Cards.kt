package blackjack

@JvmInline
value class Cards(private val cards: List<Card>) {

    init {
        require(cards == cards.distinct())
    }
}
