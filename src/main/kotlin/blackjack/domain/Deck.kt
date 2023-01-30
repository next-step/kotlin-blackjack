package blackjack.domain

class Deck {
    companion object {
        private val cards: MutableSet<Card> = buildSet {
            val cardNumbers = CardNumber.values()
            val patterns = Pattern.values()
            for (cardNumberIndex in cardNumbers.indices) {
                for (patternIndex in patterns.indices) {
                    add(Card(cardNumbers[cardNumberIndex], patterns[patternIndex]))
                }
            }
        }.toMutableSet()

        fun draw(card: Card) {
            cards.remove(card)
        }
    }
}
