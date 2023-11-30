package player

import card.PlayingCard
import card.deck.PlayerDeck

class Player(val name: String) {

    var status = Status.START
        private set

    var playerDeck = PlayerDeck()
        private set

    fun playDone() {
        updatePlayerStatus(Status.STAND)
    }

    fun saveCard(card: PlayingCard) {
        playerDeck.addCard(card)
    }

    fun updateStatus() {
        val newStatus = determineStatus()
        updatePlayerStatus(newStatus)
    }

    private fun determineStatus(): Status {
        val totalPoint = playerDeck.getResultPoint()

        if (totalPoint > 21) {
            return Status.BUST
        }

        if (isBlackJack()) {
            return Status.BLACK_JACK
        }

        return Status.PLAYING
    }

    private fun isBlackJack(): Boolean {
        val totalPoint = playerDeck.getResultPoint()
        return playerDeck.cardDeckSize() == 2 && totalPoint == 21
    }

    private fun updatePlayerStatus(status: Status) {
        this.status = status
    }
}
