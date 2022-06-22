package blackjack.player

import blackjack.card.Card

class Player(
    val name: String,
    private val playerCards: PlayerCards = PlayerCards(),
) {
    private var status: PlayerStatus = PlayerStatus.HIT

    fun addCard(card: Card) {
        playerCards.add(card)
        updateStatus()
    }

    private fun updateStatus() {
        if (isBustScore()) {
            status = PlayerStatus.BUST
        }
    }

    fun myCards(): List<Card> {
        return playerCards.toList()
    }

    private fun isBustScore(): Boolean {
        return WINNING_SCORE < playerCards.getScore()
    }

    fun wantToPlay(): Boolean {
        return status == PlayerStatus.HIT
    }

    fun stopBetting() {
        status = PlayerStatus.STAY
    }

    fun isBust(): Boolean {
        return status == PlayerStatus.BUST
    }

    companion object {
        private const val WINNING_SCORE = 21
    }
}
