package blackjack.view

import blackjack.domain.ScoreBoard

object OutputView {

    private const val winComment = "승"
    private const val loseComment = "패"
    private const val drawComment = "무"

    fun beginNameNotice(playerNames: String) {
        println("\n딜러와 ${playerNames}에게 2장의 나누었습니다.")
    }

    fun roundBeginNotice(status: List<PlayerStatus>) {
        status.forEach(::handNotice)
        println()
    }

    fun handNotice(status: PlayerStatus) {
        println("${status.name}카드: ${status.handRepresent}")
    }

    fun roundResultNotice(dealerStatus: PlayerStatus, playerStatusList: List<PlayerStatus>) {
        println()
        println("${dealerStatus.name}카드: ${dealerStatus.handRepresent} - 결과: ${dealerStatus.optimalValue}")
        playerStatusList.forEach {
            println("${it.name}카드: ${it.handRepresent} - 결과: ${it.optimalValue}")
        }
    }

    fun dealerResultNotice(dealerStatus: PlayerStatus) {
        println()
        val resultForDealer = resultForDealer(dealerStatus.scoreBoard)
        println("${dealerStatus.name}: $resultForDealer")
    }

    fun playerResultNotice(players: List<PlayerStatus>) {
        players.forEach {
            val resultForPlayer = resultForPlayer(it.scoreBoard)
            println("${it.name}: $resultForPlayer")
        }
    }

    fun dealerAddNotice() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    private fun resultForPlayer(scoreBoard: ScoreBoard): String {
        return when {
            scoreBoard.win() > 0 -> winComment
            scoreBoard.lose() > 0 -> loseComment
            else -> drawComment
        }
    }

    private fun resultForDealer(scoreBoard: ScoreBoard): String {
        return "${scoreBoard.win()}승 ${scoreBoard.draw()}무 ${scoreBoard.lose()}패"
    }
}
