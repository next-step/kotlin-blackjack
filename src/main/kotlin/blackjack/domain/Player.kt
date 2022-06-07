package blackjack.domain

interface Player {
    val name: String
    val hand: Hand
    val selectHit: () -> Boolean

    fun initialize(distributedCards: DistributedCards)
    fun hit(card: Card)
    fun canHit(): Boolean
    fun calculateHand(): Int = hand.calculate()
}

class NormalPlayer(
    override val name: String,
    override val hand: Hand = Hand.empty(),
    override val selectHit: () -> Boolean
) : Player {

    override fun initialize(distributedCards: DistributedCards) {
        val (firstCard, secondCard) = distributedCards
        hand.add(firstCard)
        hand.add(secondCard)
    }

    override fun hit(card: Card) {
        check(canHit()) {
            "플레이어의 점수가 이미 ${Hand.BLACKJACK_POINT} 이상입니다"
        }
        hand.add(card)
    }

    override fun canHit(): Boolean = calculateHand() < Hand.BLACKJACK_POINT
}
