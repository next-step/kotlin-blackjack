package blackjack.domain

class Player(
    val name: String
) {
    private val _cards = mutableListOf<Card>()
    private var _stop = false

    val cards: List<Card>
        get() {
            return _cards.toList()
        }

    val stop: Boolean
        get() {
            return this._stop
        }

    init {
        require(name.isNotBlank()) { "플레이어 이름은 한글자 이상이여야 합니다." }
    }

    fun give(card: Card) {
        require(!_cards.contains(card)) { "중복되는 카드가 있습니다." }
        _cards.add(card)
    }

    fun stopGame() {
        this._stop = true
    }
}
