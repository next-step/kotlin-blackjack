package blackjack.player

import blackjack.card.Card

class Player(val name: String, private val playerCards: PlayerCards = PlayerCards()) {
    val burst: Boolean get() = isLoosingScore()

    fun getCard(card: Card) {
        playerCards.add(card)
    }

    fun myCards(): List<Card> {
        return playerCards.get()
    }

    private fun isLoosingScore(): Boolean {
        val currentScore = BlackjackScoreCalculator.getScore(myCards())
        return WINNING_SCORE < currentScore
    }

    companion object {
        private const val WINNING_SCORE = 21
    }
}
