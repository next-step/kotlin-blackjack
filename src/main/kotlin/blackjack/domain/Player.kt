package blackjack.domain

class Player(
    val name: String,
    hand: Hand = Hand()
) : User(hand) {

    init {
        require(name.isNotBlank()) { "플레이어의 이름은 공백일 수 없습니다." }
    }

    override fun hit(card: Card) {
        hand.receive(card)
        state = State.HIT
    }

    override fun canHit(): Boolean {
        return hand.getSum() < BLACKJACK
    }
}
