package player

import card.PlayingCard

class Player(private val name: String) {

    private var _status = Status.PLAYING
    val status: Status
        get() = _status

    private var _cardList = mutableListOf<PlayingCard>()
    val cardList: List<PlayingCard>
        get() = _cardList

    fun hitDone() {
        _status = Status.STAND
    }

    fun saveCard(card: PlayingCard) {
        _cardList.add(card)
    }

    fun getName() = name
}
