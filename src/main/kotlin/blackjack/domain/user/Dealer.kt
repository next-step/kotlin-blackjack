package blackjack.domain.user

import blackjack.domain.card.Card
import blackjack.domain.status.*

class Dealer(name: String = "dealer", val hitThreshold: Int = 16) : Player(name) {

    private var resultStatuses: MutableList<Status> = mutableListOf()

    fun addResult(resultStatus: Status) {
        resultStatuses.add(resultStatus)
    }

    override fun draw(card: Card, count: Int) {
        if (cards.getScore().value > hitThreshold) {
            super.stay()
            return
        }
        super.draw(card, count)
    }

    override fun getGameResult(): GameResult {
        val winCount = resultStatuses.count { it is Win }
        val loseCount = resultStatuses.count { it is Lose }
        val drawCount = resultStatuses.count { it is Draw }
        return GameResult(winCount, loseCount, drawCount)
    }


}
