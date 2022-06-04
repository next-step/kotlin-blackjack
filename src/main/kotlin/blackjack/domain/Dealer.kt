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
            val initialCards = deck.draw() to deck.draw()
            it.initialize(initialCards)
        }
    }

    fun dealWith(player: Player) {
        check(deck.sizeOfRemaining() >= SIZE_OF_HIT) {
            "플레이어와 거래할 카드가 부족합니다"
        }
        player.hit(deck.draw())
    }
}
