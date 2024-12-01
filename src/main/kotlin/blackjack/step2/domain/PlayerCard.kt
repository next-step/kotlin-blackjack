package blackjack.step2.domain

class PlayerCard private constructor(
    val playerName: String,
    private val cards: Cards,
) {
    val allCards: List<Card>
        get() = cards.all

    fun addCard(card: Card): PlayerCard {
        cards.add(card)
        return this
    }

    fun calculateScore(): Int {
        return cards.calculateScore()
    }

    companion object {
        fun of(
            playerName: String,
            cards: List<Card>? = null,
        ): PlayerCard {
            return PlayerCard(playerName, Cards.of(cards ?: emptyList()))
        }
    }
}
