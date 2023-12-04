package blackjack.domain.model

import blackjack.domain.model.player.Dealer
import blackjack.domain.model.player.Gambler

data class Gamblers(private val gamblers: List<Gambler>) : List<Gambler> by gamblers {

    init {
        require(gamblers.size in MIN_SIZE..MAX_SIZE) {
            "겜블러들은 $MIN_SIZE~${MAX_SIZE}명 이여야 합니다."
        }
    }

    fun winDrawLoseCount(dealer: Dealer): WinDrawLoseCount {
        val winCount = gamblers.count { gambler -> gambler.winDrawLose(dealer) == WinDrawLose.LOSE }
        val drawCount = gamblers.count { gambler -> gambler.winDrawLose(dealer) == WinDrawLose.DRAW }
        val loseCount = gamblers.count { gambler -> gambler.winDrawLose(dealer) == WinDrawLose.WIN }
        return WinDrawLoseCount.of(winCount, drawCount, loseCount)
    }

    companion object {
        private const val MIN_SIZE = 1
        private const val MAX_SIZE = 10

        fun from(names: Names): Gamblers = Gamblers(names.toList().map { name -> Gambler.from(name) })
    }
}
