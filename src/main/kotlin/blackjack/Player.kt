package blackjack

class Player(
    val name: String,
) {
    private val _cards: MutableList<Card> = mutableListOf()

    val cards: List<Card>
        get() = _cards.toList()

    fun go(card: Card) {
        _cards.add(card)
    }

    fun stop(): Int {
        return ScoreCalculator.calc(cards)
    }

}