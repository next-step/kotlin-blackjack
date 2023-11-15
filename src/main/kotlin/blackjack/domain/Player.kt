package blackjack.domain

data class Player(
    val name: String,
    val hand: Hand = Hand()
) {

    init {
        require(name.isNotBlank()) { "플레이어의 이름은 공백일 수 없습니다." }
    }

    fun init(deck: Deck) {
        val initCards = deck.init()
        hand.init(initCards[0], initCards[1])
    }

    fun canHit(): Boolean {
        return hand.canHit()
    }

    fun hit(deck: Deck) {
        val card = deck.hit()
        hand.receive(card)
    }
}
