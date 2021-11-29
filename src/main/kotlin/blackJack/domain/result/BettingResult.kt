package blackJack.domain.result

import blackJack.domain.player.Player

class BettingResult(val profit: Int) {

    companion object {
        fun of(player: Player, winDrawLose: WinDrawLose): BettingResult =
            when (winDrawLose) {
                WinDrawLose.WIN -> (player.getBettingMoney() * selectTimes(player.isBlackJackPlayer())).toInt()
                WinDrawLose.DRAW -> NOT_PROFIT
                WinDrawLose.LOSE -> -player.getBettingMoney()
            }.run {
                BettingResult(this)
            }

        private fun selectTimes(isBlackJackPlayer: Boolean): Double = when (isBlackJackPlayer) {
            true -> BlACK_JACK_TIMES
            false -> ORDINARILY_TIMES
        }

        private const val BlACK_JACK_TIMES = 1.5
        private const val ORDINARILY_TIMES = 1.0
        private const val NOT_PROFIT = 0
    }
}
