package blackjack

class Hand(
    initialCards: List<Card> = emptyList(),
    private val blackJackCardSumCalculator: BlackJackCardSumCalculator =
        DefaultBlackJackCardSumCalculator(),
) {
    private val _cards = initialCards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    fun add(newCard: Card) {
        _cards.add(newCard)
    }

    fun sumOfHand(): Int = blackJackCardSumCalculator.sum(_cards)

    fun isBust(): Boolean = sumOfHand() > BLACKJACK_NUMBER

    companion object {
        private const val BLACKJACK_NUMBER = 21
    }
}
