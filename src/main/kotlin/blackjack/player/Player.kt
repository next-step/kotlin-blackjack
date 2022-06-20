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
        updateStatus()
    }

    private fun updateStatus() {
        if (bust) {
            stopBetting()
        }
    }

    fun myCards(): List<Card> {
        return playerCards.get()
    }

    private fun isLoosingScore(): Boolean {
        return WINNING_SCORE < score
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
