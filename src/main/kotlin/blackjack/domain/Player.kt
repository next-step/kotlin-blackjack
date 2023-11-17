package blackjack.domain

class Player(
    val name: String,
    val hand: Hand = Hand()
) {

    init {
        require(name.isNotBlank()) { "플레이어의 이름은 공백일 수 없습니다." }
    }

    fun init(cards: List<Card>) {
        require(cards.size == 2) { "처음엔 카드 2장만 받을 수 있습니다." }
        hand.init(cards[0], cards[1])
    }

    fun canHit(): Boolean {
        return hand.canHit()
    }

    fun hit(card: Card) {
        hand.receive(card)
    }
}
