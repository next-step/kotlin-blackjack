package blackjack.player

import blackjack.card.Card

class Player(
    val name: String,
    private val playerCards: PlayerCards = PlayerCards(),
) {
    private var status: BET_STATUS = BET_STATUS.HIT
    private val score: Int get() = BlackjackScoreCalculator.getScore(myCards())
    val bust: Boolean get() = isLoosingScore()

    fun getCard(card: Card) {
        playerCards.add(card)
    }

    fun myCards(): List<Card> {
        return playerCards.get()
    }

    private fun isLoosingScore(): Boolean {
        val isBust = WINNING_SCORE < score
        if (isBust) {
            stopBetting()
        }
        return isBust
    }

    fun wantToPlay(): Boolean {
        return status == BET_STATUS.HIT
    }

    fun stopBetting() {
        status = BET_STATUS.STAY
    }

    companion object {
        private const val WINNING_SCORE = 21
    }
}
