package blackjack.step2.domain

class PlayerCard private constructor(
    val playerName: String,
    private val cards: Cards,
) {
    val allCards: List<Card>
        get() = cards.all

    fun pickCard(card: Card): PlayerCard {
        return PlayerCard(playerName, cards.add(card))
    }

    fun calculateScore(): Int {
        return ScoreCalculator.calculate(cards)
    }

    companion object {
        fun of(
            playerName: String,
            cards: List<Card> = emptyList(),
        ): PlayerCard {
            return PlayerCard(playerName, Cards.of(cards))
        }
    }
}
