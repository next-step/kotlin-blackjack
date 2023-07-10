package blackjack.domain

class BlackjackGame(
    private val dealer: Dealer,
    val players: List<Player>
) {
    init {
        initGame()
    }

    private fun initGame() {
        repeat(INIT_DEAL_COUNT) { initDeal() }
    }

    private fun initDeal() {
        players.forEach { player -> dealer.deal(player) }
    }

    fun isNotFinished(): Boolean = players.any { it.state == PlayerState.PLAYING }

    companion object {
        private const val INIT_DEAL_COUNT = 2
    }
}
