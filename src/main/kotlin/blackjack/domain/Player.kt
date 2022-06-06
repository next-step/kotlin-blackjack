package blackjack.domain

class Player(
    val name: String,
    val hand: Hand = Hand.empty(),
    val selectHit: () -> Boolean
) {
    fun initialize(distributedCards: DistributedCards) {
        val (firstCard, secondCard) = distributedCards
        hand.add(firstCard)
        hand.add(secondCard)
    }

    fun hit(card: Card) {
        check(canHit()) {
            "플레이어의 점수가 이미 ${Hand.BLACKJACK_POINT} 이상입니다"
        }
        hand.add(card)
    }

    fun calculateHand(): Int = hand.calculate()

    fun canHit(): Boolean = calculateHand() < Hand.BLACKJACK_POINT
}
