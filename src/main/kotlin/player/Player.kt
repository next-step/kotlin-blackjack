package player

import BlackJackCalculator
import card.PlayingCard

class Player(private val name: String) {

    private var _status = Status.PLAYING
    val status: Status
        get() = _status

    private var _cardList = mutableListOf<PlayingCard>()
    val cardList: List<PlayingCard>
        get() = _cardList

    fun playDone() {
        updatePlayerStatus(Status.STAND)
    }

    fun saveCard(card: PlayingCard) {
        _cardList.add(card)
    }

    fun getName() = name
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
        _status = status
    }
}
