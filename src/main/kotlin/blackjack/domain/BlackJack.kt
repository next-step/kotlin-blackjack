package blackjack.domain

class BlackJack(
    val players: List<Player>,
    val dealer: Dealer = Dealer(),
    private val gameCards: GameCards = GameCards(),
) {
    fun distributeInitialCard() {
        dealer.addCards(drawInitCards())
        players.forEach { player -> player.addCards(drawInitCards()) }
    }

    private fun drawInitCards(): List<Card> {
        return List(START_CARD_COUNT) { gameCards.draw() }
    }

    fun distributeCardForDealer() {
        dealer.addCard(gameCards.draw())
    }

    fun isEnd(): Boolean {
        return players.none { it.canProceedTurn() }
    }

    fun getNowPlayer(): Player {
        return players.firstOrNull { it.canProceedTurn() } ?: throw RuntimeException(PLAYER_NONE_EXCEPTION)
    }

    fun playGameTurn(isPlaying: Boolean) {
        val nowPlayer = getNowPlayer()
        when (isPlaying) {
            true -> nowPlayer.addCard(gameCards.draw())
            false -> nowPlayer.finishedTurn()
        }
    }

    fun shouldDealerDrawCard(): Boolean {
        if (dealer.score() <= DEALER_CARD_STANDARD_SCORE) {
            return true
        }
        return false
    }

    fun getResult(): Ranks {
        return Ranks(players.associateWith { PlayerRank.of(it.score(), dealer.score()) })
    }

    companion object {
        private const val START_CARD_COUNT = 2
        const val BLACKJACK_MAX_SCORE = 21
        private const val DEALER_CARD_STANDARD_SCORE = 16
        private const val PLAYER_NONE_EXCEPTION = "턴을 가져갈 플레이어가 존재하지 않습니다"
    }
}
