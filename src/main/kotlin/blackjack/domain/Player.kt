package blackjack.domain

class Player(
    val name: String,
    val hand: Hand = Hand.empty(),
    val selectHit: () -> Boolean
) {
    fun initialize(initialCards: Pair<Card, Card>) {
        with(initialCards) {
            hand.add(first)
            hand.add(second)
        }
    }

    fun hit(card: Card) {
        check(canHit()) {
            "플레이어의 점수가 이미 $BLACKJACK_POINT 이상입니다"
        }
        hand.add(card)
    }

    fun calculateHand(): Int = hand.calculate()

    fun canHit(): Boolean = calculateHand() < BLACKJACK_POINT
}
