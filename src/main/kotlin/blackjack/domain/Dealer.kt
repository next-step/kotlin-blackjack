package blackjack.domain

interface Dealer {
    fun distribute(): DistributedCards
    fun dealWith(player: Player): Boolean
}

class Croupier(
    private val deck: Deck = ShuffledDeck(),
    private val player: Player = NormalPlayer(DEALER_NAME)
) : Dealer, Player by player {

    override val selectHit: () -> Boolean = { true }

    override fun distribute(): DistributedCards {
        check(deck.sizeOfRemaining() >= SIZE_OF_DISTRIBUTION) { "분배할 카드가 부족합니다" }
        return DistributedCards(deck.draw(), deck.draw())
    }

    override fun dealWith(player: Player): Boolean {
        return when (player.canHit() && player.selectHit()) {
            true -> {
                check(deck.sizeOfRemaining() >= SIZE_OF_HIT) {
                    "플레이어와 거래할 카드가 부족합니다"
                }
                player.hit(deck.draw())
                true
            }
            false -> false
        }
    }

    override fun hit(card: Card) {
        check(canHit()) { "딜러의 점수가 이미 $HITTABLE_UPPERBOUND 이상입니다." }
        hand.add(card)
    }

    override fun canHit(): Boolean = hand.calculate() <= HITTABLE_UPPERBOUND

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val SIZE_OF_HIT = 1
        private const val SIZE_OF_DISTRIBUTION = 2
        const val HITTABLE_UPPERBOUND = 16
    }
}
