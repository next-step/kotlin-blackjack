package blackJack.domain

class Result(players: Players, private val dealer: Dealer) {
    var dealerProfit: Int = DEFAULT_PROFIT
        private set

    val playersProfit = players.makeMap { getWinOrLose(it) }

    private fun getWinOrLose(player: Player): WinOrLose {
        return when {
            checkWinner(player) -> {
                dealerProfit -= player.betMoney
                WinOrLose.WIN
            }
            checkDrawPlayer(player) && checkBust(player) ?: true -> {
                WinOrLose.DRAW
            }
            else -> {
                dealerProfit += player.betMoney
                WinOrLose.LOSE
            }
        }
    }

    private fun checkWinner(player: Player): Boolean {
        return checkBust(player) ?: checkTotalScore(player)
    }

    private fun checkBust(player: Player): Boolean? {
        if (player.isBust()) {
            return false
        }
        if (dealer.isBust()) {
            return true
        }
        return null
    }

    private fun checkTotalScore(player: Player): Boolean = player.getTotalScore() > dealer.getTotalScore()

    private fun checkDrawPlayer(player: Player): Boolean = player.getTotalScore() == dealer.getTotalScore()

    companion object {
        private const val DEFAULT_PROFIT = 0
    }
}
