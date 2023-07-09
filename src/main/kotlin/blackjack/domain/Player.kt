package blackjack.domain

class Player(val name: String, val hands: Hands) {
    init {
        require(name.isNotBlank()) { "플레이어의 이름이 비어있습니다." }
    }

    fun hit(card: Card) {
        hands.hit(card)
    }
}
