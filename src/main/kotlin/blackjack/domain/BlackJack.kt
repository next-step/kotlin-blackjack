package blackjack.domain

class BlackJack(val players: List<Player>, private val gameCards: GameCards = GameCards()) {

    fun start() {
        distributeInitialCard()
    }

    private fun distributeInitialCard() {
        for (i in 0 until START_CARD_COUNT) players.forEach { it.addCard(gameCards.draw()) }
    }

    fun isEnd(): Boolean {
        return players.all { !it.canProceedTurn() }
    }

    fun getNowPlayer(): Player {
        return players.first { it.canProceedTurn() }
    }

    fun playGameTurn(isPlaying: Boolean) {
        when (isPlaying) {
            true -> getNowPlayer().addCard(gameCards.draw())
            false -> getNowPlayer().finishedTurn()
        }
    }

    companion object {
        private const val START_CARD_COUNT = 2
        const val BLACKJACK_MAX_SCORE = 21
    }
}
