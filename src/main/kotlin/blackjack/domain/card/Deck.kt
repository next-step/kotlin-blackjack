package blackjack.domain.card

import blackjack.domain.game.Game

data class Deck(
    private val cards: MutableList<Card> = mutableListOf()
) {
    fun add(card: Card) = cards.add(card)

    fun contains(card: Card) = cards.contains(card)

    fun getCards() = cards.toList()

    fun getScore(): Int {
        val deckForCalculate = cards.toMutableList().sortedByDescending { it.getScore() }

        var score = 0
        for (card in deckForCalculate) {
            score += card.getScore(score)
            validateScore(score)
        }

        return score
    }

    private fun validateScore(score: Int) {
        require(score <= Game.WINNING_NUMBER) {
            "카드 숫자 합이 21을 초과할 수 없습니다."
        }
    }
}
