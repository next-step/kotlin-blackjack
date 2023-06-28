package blackjack.scorerule.view

import blackjack.scorerule.domain.ScoreBoard

object ScoreOutputView {

    private const val winComment = "승"
    private const val loseComment = "패"
    private const val drawComment = "무"

    fun roundBeginNotice(status: List<ScorePlayerStatus>) {
        status.forEach(ScoreOutputView::handNotice)
        println()
    }

    fun handNotice(status: ScorePlayerStatus) {
        println("${status.name}카드: ${status.handRepresent}")
    }

    fun roundResultNotice(dealerStatus: ScorePlayerStatus, scorePlayerStatusList: List<ScorePlayerStatus>) {
        println()
        println("${dealerStatus.name}카드: ${dealerStatus.handRepresent} - 결과: ${dealerStatus.optimalValue}")
        scorePlayerStatusList.forEach {
            println("${it.name}카드: ${it.handRepresent} - 결과: ${it.optimalValue}")
        }
    }

    fun dealerResultNotice(dealerStatus: ScorePlayerStatus) {
        println()
        val resultForDealer = resultForDealer(dealerStatus.scoreBoard)
        println("${dealerStatus.name}: $resultForDealer")
    }

    fun playerResultNotice(players: List<ScorePlayerStatus>) {
        players.forEach {
            val resultForPlayer = resultForPlayer(it.scoreBoard)
            println("${it.name}: $resultForPlayer")
        }
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
