package blackjack.step2.domain

class Player private constructor(
    val playerName: String,
    private val cards: Cards,
) {
    val allCards: List<Card>
        get() = cards.all

    fun pickCard(card: Card): Player {
        return Player(playerName, cards.add(card))
    }

    fun calculateScore(): Int {
        return ScoreCalculator.calculate(cards)
    }

    companion object {
        fun of(
            playerName: String,
            cards: List<Card> = emptyList(),
        ): Player {
            return Player(playerName, Cards.of(cards))
        }
    }
}
