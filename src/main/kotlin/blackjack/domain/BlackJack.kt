package blackjack.domain

class BlackJack(
    val players: List<Player>,
    val dealer: Dealer = Dealer(),
    private val gameCards: GameCards = GameCards(),
) {

    fun start() {
        distributeInitialCard()
    }

    private fun distributeInitialCard() {
        for (i in 0 until START_CARD_COUNT) {
            dealer.addCard(gameCards.draw())
            players.forEach { it.addCard(gameCards.draw()) }
        }
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

    fun askDealerForAdditionalCard(): Boolean {
        if (dealer.score() <= DEALER_CARD_STANDARD_SCORE) {
            dealer.addCard(gameCards.draw())
            return true
        }
        return false
    }

    companion object {
        const val START_CARD_COUNT = 2
        const val BLACKJACK_MAX_SCORE = 21
        const val DEALER_CARD_STANDARD_SCORE = 16
    }
}
