package blackjack.domain

class BlackJack(val players: List<Player>, private val gameCards: GameCards = GameCards()) {

    private var nowPlayer = 0
    private var playCount = 0
    private var isEnd = false

    fun start() {
        distributeInitialCard()
    }

    private fun distributeInitialCard() {
        for (i in 0 until START_CARD_COUNT) players.forEach { it.addCard(gameCards.draw()) }
    }

    fun isEnd(): Boolean {
        if (!isEnd) {
            checkAndChangeGamePlayer()
        }
        return isEnd
    }

    fun getNowPlayer(): Player {
        return players[nowPlayer]
    }

    fun playGameTurn(isPlaying: Boolean): Int {
        val count = playCount
        when (isPlaying) {
            true -> {
                getNowPlayer().addCard(gameCards.draw())
                playCount++
            }

            false -> changeNowPlayer()
        }
        return count
    }

    private fun checkAndChangeGamePlayer() {
        if (players[nowPlayer].score() > BLACKJACK_MAX_SCORE) {
            changeNowPlayer()
        }
    }

    private fun changeNowPlayer() {
        nowPlayer++
        playCount = 0
        if (nowPlayer >= players.size) {
            isEnd = true
        }
    }

    companion object {
        private const val START_CARD_COUNT = 2
        const val BLACKJACK_MAX_SCORE = 21
    }
}
