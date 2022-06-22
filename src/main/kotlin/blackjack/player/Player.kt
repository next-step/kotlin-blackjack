package blackjack.player

import blackjack.card.Card

class Player(
    val name: String,
    private val playerCards: PlayerCards = PlayerCards(),
) {
    private var status: BetStatus = BetStatus.HIT
    private val score: Int get() = BlackJackScoreCalculator.getScore(myCards())
    val bust: Boolean get() = isLoosingScore()

    fun addCard(card: Card) {
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
        return status == BetStatus.HIT
    }

    fun stopBetting() {
        status = BetStatus.STAY
    }

    companion object {
        private const val WINNING_SCORE = 21
    }
}
