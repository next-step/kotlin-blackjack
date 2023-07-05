package step2.domain

class Cards(
    val cards: MutableSet<Card> = mutableSetOf()
) {

    private val cardCurrentScore: CardCurrentScore = CardCurrentScore()

    fun draw(count: Int) {
        cards.addAll(CardDeck.draw(count))
    }

    fun calculateScore(): CardCurrentScore {
        cardCurrentScore.calculateScore(cards)
        return cardCurrentScore
    }
}
