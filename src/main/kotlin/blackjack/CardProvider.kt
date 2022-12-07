package blackjack

class CardProvider {
    var players = emptyList<Player>()
        private set

    fun addPlayer(player: Player) {
        players = players.toMutableList().apply {
            add(player)
        }
    }

    fun start() {
        players.forEach { player ->
            takeCard(player, START_TAKE_CARD_COUNT)
        }
    }

    private fun takeCard(player: Player, count: Int) {
        repeat(count) {
            player.takeCard(Card())
        }
    }

    companion object {
        private const val START_TAKE_CARD_COUNT = 2
    }
}
