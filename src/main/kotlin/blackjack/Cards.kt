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
}
