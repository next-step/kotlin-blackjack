package blackjack

@JvmInline
value class Cards(private val cards: List<Card>) {

    init {
        require(cards == cards.distinct())
    }

    fun add(card: Card) = Cards(cards + card)

    operator fun contains(card: Card): Boolean = card in cards

    companion object {
        fun empty() = Cards(emptyList())
    }
}
