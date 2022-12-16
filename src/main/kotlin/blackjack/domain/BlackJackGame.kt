package blackjack.domain

class BlackJackGame(
    val cardManager: CardManager,
    val players: List<Player>
) {
    init {
        repeat(INITIAL_CARD_SIZE) {
            setUp()
        }
    }

    private fun setUp() {
        players.forEach {
            it.give(cardManager.getCard())
        }
    }

    fun play() {
        TODO()
    }

    companion object {
        private const val INITIAL_CARD_SIZE = 2
    }
}
