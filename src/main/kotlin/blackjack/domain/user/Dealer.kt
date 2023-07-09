package blackjack.domain.user

import blackjack.domain.card.Card
import blackjack.domain.status.*

class Dealer(name: String = "dealer", val hitThreshold: Int = 16) : Player(name) {

    private var resultStatuses: MutableList<Status> = mutableListOf()

    override fun draw(card: Card, count: Int) {
        if (cards.getScore().value > hitThreshold) {
            status = status.stay()
            return
        }
        status = status.draw(card)
    }

    override fun getGameResult(): GameResult {
        val winCount = resultStatuses.count { it is Win }
        val loseCount = resultStatuses.count { it is Lose }
        val drawCount = resultStatuses.count { it is Draw }
        return GameResult(winCount, loseCount, drawCount)
    }

    fun judgeResult(playerGroup: PlayerGroup) {
        playerGroup.players.forEach { player ->
            val resultStatus = player.status.judge(this)
            player.status = resultStatus

            resultStatus as ResultStatus
            resultStatuses.add(resultStatus.reverse())
        }
    }

}
