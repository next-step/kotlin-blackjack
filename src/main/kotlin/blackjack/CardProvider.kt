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

    fun play() {
        players.forEach { player ->
            do {
                if (player.func().equals("y", true)) {
                    takeCard(player, 1)
                } else {
                    break
                }
                val cardCalculator = CardCalculator(player.takeCards)
            } while (cardCalculator.sum() < CardCalculator.STD_NUMBER)
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
