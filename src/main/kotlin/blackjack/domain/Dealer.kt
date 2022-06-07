package blackjack.domain

interface Dealer {
    fun distribute(players: List<Player>)
    fun dealWith(player: Player): Boolean
}

class Croupier(
    private val deck: Deck = ShuffledDeck(),
    override val name: String = DEALER_NAME,
    override val hand: Hand = Hand.empty(),
) : Player, Dealer {

    override val selectHit: () -> Boolean = { true }

    override fun distribute(players: List<Player>) {
        require(players.isNotEmpty()) { "카드를 분배할 플레이어가 없습니다" }
        check(deck.sizeOfRemaining() >= players.size * SIZE_OF_DISTRIBUTION) {
            "플레이어에게 분배할 카드가 부족합니다"
        }
        players.forEach {
            it.initialize(DistributedCards(deck.draw(), deck.draw()))
        }
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

    override fun initialize(distributedCards: DistributedCards) {
        TODO()
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
        private const val HITTABLE_UPPERBOUND = 16
    }
}
