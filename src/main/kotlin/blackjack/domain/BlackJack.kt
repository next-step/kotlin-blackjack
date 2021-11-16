package blackjack.domain

class BlackJack(val players: List<Player>) {
    val deck = CardDeck()

    init {
        require(players.size >= MINIMUM_NUMBER_OF_PLAYERS) { NOT_ENOUGH_PLAYERS_ERROR_MSG }
        require(players.distinctBy { it.name } == this.players) { DUPLICATE_PLAYERS_ERROR_MSG }
    }

    fun drawInitialCards() {
        players.forEach {
            val cards = deck.drawMany(INITIAL_DRAW_COUNT)
            it.takeCards(cards)
        }
    }

    fun drawAnotherCard(player: Player) {
        val card = deck.drawOne()
        player.takeCards(card)
    }

    companion object {
        private const val INITIAL_DRAW_COUNT = 2
        private const val MINIMUM_NUMBER_OF_PLAYERS = 2
        private const val NOT_ENOUGH_PLAYERS_ERROR_MSG = "플레이어의 수가 부족합니다."
        private const val DUPLICATE_PLAYERS_ERROR_MSG = "플레이어의 이름은 중복될 수 없습니다."
    }
}
