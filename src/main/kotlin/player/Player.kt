package player

import BlackJackCalculator
import card.PlayingCard

class Player(val name: String) {

    var status = Status.PLAYING
        private set

    private var _cardList = mutableListOf<PlayingCard>()
    val cardList: List<PlayingCard>
        get() = _cardList

    fun playDone() {
        updatePlayerStatus(Status.STAND)
    }

    fun saveCard(card: PlayingCard) {
        _cardList.add(card)
    }

    fun updateStatus() {
        val newStatus = determineStatus()
        updatePlayerStatus(newStatus)
    }

    private fun determineStatus(): Status {
        val totalPoint = BlackJackCalculator.calculate(_cardList)
        return when {
            (totalPoint <= 20) -> Status.PLAYING
            else -> Status.STAND
        }
    }

    private fun updatePlayerStatus(status: Status) {
        this.status = status
    }
}
