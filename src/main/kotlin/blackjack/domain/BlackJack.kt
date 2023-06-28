package blackjack.domain

class BlackJack(
    val players: List<Player>,
    val dealer: Dealer = Dealer(),
    private val gameCards: GameCards = GameCards(),
) {
    fun distributeInitialCard() {
        for (i in 0 until START_CARD_COUNT) {
            distributeCardForDealer()
            distributeCardForPlayers()
        }
    }

    fun distributeCardForDealer() {
        dealer.addCard(draw())
    }

    private fun distributeCardForPlayers() {
        players.forEach { it.addCard(draw()) }
    }

    private fun draw(): Card {
        return gameCards.draw()
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
        val result = players.associateWith {
            getPlayerRank(it)
        }
        return Ranks(result)
    }

    private fun getPlayerRank(player: Player): PlayerRank {
        return PlayerRank.getValues(player.score(), dealer.score())
    }

    companion object {
        const val START_CARD_COUNT = 2
        const val BLACKJACK_MAX_SCORE = 21
        const val DEALER_CARD_STANDARD_SCORE = 16
        private const val PLAYER_NONE_EXCEPTION = "턴을 가져갈 플레이어가 존재하지 않습니다"
    }
}
