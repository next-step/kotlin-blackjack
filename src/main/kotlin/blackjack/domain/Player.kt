package blackjack.domain

data class Player(
    val name: String,
    val hand: Hand = Hand()
) {

    init {
        require(name.isNotBlank()) { "플레이어의 이름은 공백일 수 없습니다." }
    }

    fun canHit(): Boolean {
        return hand.canHit()
    }

    fun hit(deck: Deck): Player {
        return copy(hand = hand.hit(deck))
    }
}
