package blackjack

class Cards(
    private val cards: List<Card>,
    private val blackJackCardSumCalculator: BlackJackCardSumCalculator = DefaultBlackJackCardSumCalculator(),
) {
    fun add(card: Card): Cards {
        val target = cards.toMutableList()
        target.add(card)
        return Cards(target.toList())
    }

    fun size(): Int = cards.size

    fun toList(): List<Card> = cards.toList()

    fun sum(): Int = blackJackCardSumCalculator.sum(cards)

    fun isBust(): Boolean = sum() > BLACKJACK_NUMBER

    fun isBlackjack(): Boolean = size() == BLACKJACK_CARD_SIZE && sum() == BLACKJACK_NUMBER

    companion object {
        private const val BLACKJACK_NUMBER = 21
        private const val BLACKJACK_CARD_SIZE = 2
    }
}
