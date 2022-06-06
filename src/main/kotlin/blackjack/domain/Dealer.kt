package blackjack.domain

class Dealer(
    private val deck: Deck = ShuffledDeck(),
) {

    fun distribute(players: List<Player>) {
        require(players.isNotEmpty()) { "카드를 분배할 플레이어가 없습니다" }
        check(deck.sizeOfRemaining() >= players.size * SIZE_OF_DISTRIBUTION) {
            "플레이어에게 분배할 카드가 부족합니다"
        }
        players.forEach {
            it.initialize(DistributedCards(deck.draw(), deck.draw()))
        }
    }

    fun dealWith(player: Player): Boolean {
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

    companion object {
        const val SIZE_OF_HIT = 1
        const val SIZE_OF_DISTRIBUTION = 2
    }
}
