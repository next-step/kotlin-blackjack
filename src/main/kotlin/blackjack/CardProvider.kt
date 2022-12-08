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
            play(player)
        }
    }

    private fun play(player: Player) {
        while (CardCalculator(player.takeCards).sum() < CardCalculator.STD_NUMBER) {
            if (playerAllow(player).not())
                break
        }
    }

    private fun playerAllow(player: Player): Boolean {
        return if (player.allowed().equals("y", true)) {
            takeCard(player, 1)
            true
        } else {
            false
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
