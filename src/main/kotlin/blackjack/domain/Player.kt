package blackjack.domain

class Player(
    val name: String,
    val hand: Hand = Hand(),
) {
    val value: Int
        get() = hand.value()

    init {
        require(name.isNotBlank()) { "이름이 빈 문자열입니다." }
    }

    fun drawFrom(deck: Deck) {
        hand.drawFrom(deck)
    }
}
