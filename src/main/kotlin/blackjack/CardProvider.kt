package blackjack

class CardProvider(private val players: List<Player>, private val cardRepository: CardRepository) {
    fun start() {
        players.forEach { player ->
            takeCard(player, START_TAKE_CARD_COUNT)
        }
    }

    fun hasAllowTakeCard(player: Player, allow: String): Boolean {
        return if (allow.equals(Player.ALLOW_TEXT, true)) {
            hasNextTakeCard(player)
        } else {
            false
        }
    }

    private fun hasNextTakeCard(player: Player): Boolean {
        takeCard(player, DEFAULT_TAKE_CARD_COUNT)
        return CardCalculator(player.getTakeCards()).sum() < CardCalculator.STD_NUMBER
    }

    private fun takeCard(player: Player, count: Int) {
        repeat(count) {
            player.takeCard(cardRepository.getCard())
        }
    }

    companion object {
        private const val DEFAULT_TAKE_CARD_COUNT = 1
        private const val START_TAKE_CARD_COUNT = 2
    }
}
