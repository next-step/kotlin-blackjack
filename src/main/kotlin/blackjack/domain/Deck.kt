package blackjack.domain

object Deck {

    private val cards: MutableMap<Card, Boolean> = buildMap {
        val cardNumbers = CardNumber.values()
        val patterns = Pattern.values()
        for (cardNumberIndex in cardNumbers.indices) {
            for (patternIndex in patterns.indices) {
                put(Card(cardNumbers[cardNumberIndex], patterns[patternIndex]), false)
            }
        }
    }.toMutableMap()

    fun isDrawable(card: Card): Boolean = (this.cards[card] != false)

    fun draw(card: Card) {
        cards[card] = true
    }
}
