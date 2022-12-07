package blackjack.domain

data class Gamer(val name: String, val cards: Cards = Cards()) {

    override fun toString(): String {
        return "${name}카드: $cards"
    }

    fun getTotalScore(): Int {
        val totalScoreByAceEleven = cards.getTotalScore(isAceEleven = true)
        val diffByAceEleven = totalScoreByAceEleven - Companion.BLACK_JACK
        val totalScoreByAceOne = cards.getTotalScore(isAceEleven = false)
        val diffByAceOne = totalScoreByAceOne - BLACK_JACK

        return if (diffByAceEleven < diffByAceOne) {
            totalScoreByAceEleven
        } else {
            totalScoreByAceOne
        }
    }

    fun addCard(card: Card) = cards.addCard(card)

    fun hasCard(card: Card): Boolean = cards.hasCard(card)

    companion object {
        private const val BLACK_JACK = 21
    }
}
