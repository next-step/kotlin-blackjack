package blackjack.domain

class Player(val name: String, val cards: Cards = Cards()) {
    init {
        require(!name.isNullOrBlank()) { "이름은 빈값이 될 수 없습니다." }
    }
}
