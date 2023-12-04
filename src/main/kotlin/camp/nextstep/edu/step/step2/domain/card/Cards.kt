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

    /**
     * @description : 카드의 합을 계산한다. 만약 합이 21이 넘는다면 에이스를 1로 계산한다.
     */
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

    /**
     * @description : 에이스를 포함한 카드의 합을 계산한다. 에이스가 없다면 에이스를 포함하지 않은 카드의 합을 반환한다.
     */
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
