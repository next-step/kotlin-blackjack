package player

import card.PlayingCard
import card.deck.PlayerDeck

class Player(val name: String) {

    var status = Status.START
        private set

    var playerDeck = PlayerDeck()
        private set

    fun playDone() {
        this.status = Status.STAND
    }

    fun saveCard(card: PlayingCard) {
        playerDeck.addCard(card)
        updateStatus()
    }

    fun updateStatus() {
        val totalPoint = playerDeck.getResultPoint()

        if (totalPoint > BLACKJACK_NUMBER) {
            this.status = Status.BUST
        } else if (isBlackJack()) {
            this.status = Status.BLACK_JACK
        } else {
            this.status = Status.PLAYING
        }
    }

    fun getResultPoint(): Int {
        return playerDeck.getResultPoint()
    }

    private fun isBlackJack(): Boolean {
        val totalPoint = playerDeck.getResultPoint()
        return playerDeck.cardDeckSize() == BLACKJACK_CARD_COUNT && totalPoint == BLACKJACK_NUMBER
    }

    companion object {
        private const val BLACKJACK_CARD_COUNT = 2
        private const val BLACKJACK_NUMBER = 21
    }
}
