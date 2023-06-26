package blackjack.domain

class BlackJack(val players: List<Player>) {

    private var nowPlayer = 0
    private var playCount = 0
    private var isEnd = false

    fun start() {
        for (i in 0 until START_CARD_COUNT) players.forEach { it.draw() }
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

    fun play(answer: String): Int {
        val count = playCount
        when (answer) {
            "y" -> {
                getNowPlayer().draw()
                playCount++
            }
            "n" -> changeNowPlayer()
            else -> throw IllegalArgumentException("잘못된 답변입니다")
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
