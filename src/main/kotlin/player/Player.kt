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
    }

    fun updateStatus() {
        val totalPoint = playerDeck.getResultPoint()

        if (totalPoint > 21) {
            this.status = Status.BUST
        } else if (isBlackJack()) {
            this.status = Status.BLACK_JACK
        } else {
            this.status = Status.PLAYING
        }
    }

    private fun isBlackJack(): Boolean {
        val totalPoint = playerDeck.getResultPoint()
        return playerDeck.cardDeckSize() == 2 && totalPoint == 21
    }
}
