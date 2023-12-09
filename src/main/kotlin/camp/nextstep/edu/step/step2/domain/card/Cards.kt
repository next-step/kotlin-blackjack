package camp.nextstep.edu.step.step2.domain.card

class Cards(
    cards: List<Card>
) {
    private val cards = cards.toMutableList()

    init {
        require(cards.isNotEmpty()) { "카드가 입력되지 않았습니다." }
    }

    fun getCards(): List<Card> {
        return cards
    }

    fun sumCards(): Int {
        if (BLACKJACK < sumCardsWithAce()) {
            return sumOfCardsScore()
        }
        return sumCardsWithAce()
    }

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun isLessThanBlackJack(): Boolean {
        return sumCards() < BLACKJACK
    }

    private fun sumCardsWithAce(): Int {
        val hasAce = cards.any { it.isAce() }

        if (hasAce) {
            return sumOfCardsScore() + 10
        }

        return sumOfCardsScore()
    }

    private fun sumOfCardsScore(): Int {
        return cards.sumOf { it.getCardNumber() }
    }

    companion object {
        private const val BLACKJACK = 21
    }

}
