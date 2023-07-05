package blackjack.domain

class BlackJackGame(
    private val dealer: Dealer,
    val players: List<Player>
) {
    init {
        initGame()
    }

    private fun initGame() {
        repeat(INIT_DEAL_COUNT) { deal() }
    }

    private fun deal() {
        players.forEach { player -> dealer.deal(player) }
    }

    companion object {
        private const val INIT_DEAL_COUNT = 2
    }
}
