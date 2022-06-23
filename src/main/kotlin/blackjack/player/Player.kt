package blackjack.player

import blackjack.card.Card

class Player(
    val name: String,
    private val playerCards: PlayerCards = PlayerCards(),
) {
    val score: Int = playerCards.getScore()
    private var status: PlayerStatus = PlayerStatus.HIT

    fun addCard(card: Card) {
        playerCards.add(card)
        updateStatus()
    }

    private fun updateStatus() {
        if (playerCards.isBustScore()) {
            status = PlayerStatus.BUST
        }
    }

    fun myCards(): List<Card> {
        return playerCards.toList()
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
}
