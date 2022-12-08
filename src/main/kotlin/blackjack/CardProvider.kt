package blackjack

class CardProvider(private val players: List<Player>) {
    fun start() {
        players.forEach { player ->
            takeCard(player, START_TAKE_CARD_COUNT)
        }
    }

    fun play(result: (player: Player) -> Unit) {
        players.forEach { player ->
            play(player, result)
        }
    }

    private fun play(player: Player, result: (player: Player) -> Unit) {
        while (CardCalculator(player.takeCards).sum() < CardCalculator.STD_NUMBER) {
            if (playerAllow(player).not()) {
                result(player)
                break
            }

            result(player)
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
