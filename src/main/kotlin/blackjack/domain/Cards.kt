package blackjack.domain

class Cards(cards: List<Card> = emptyList()) {

    private val cardList: MutableList<Card> = cards.toMutableList()

    val totalScore: Int
        get() = calculate()

    fun getCardList(): List<Card> = cardList.toList()

    fun add(card: Card) = cardList.add(card)

    fun hasCard(card: Card): Boolean = cardList.contains(card)

    private fun hasAce(): Boolean {
        return cardList.any { card -> card.cardNumber == CardNumber.Ace }
    }

    private fun calculate(): Int {
        val totalScore = cardList.sumOf { card -> card.cardNumber.score }
        return if (totalScore <= BONUS_SCORE_CRITERIA && hasAce()) {
            totalScore + BONUS_ACE_SCORE
        } else {
            totalScore
        }
    }

    companion object {
        private const val BONUS_SCORE_CRITERIA = 11
        private const val BONUS_ACE_SCORE = 10
    }
}
