package blackJack.domain

class Result(private val dealer: Dealer, players: Players) {
    private val _dealerResult = WinOrLose.values().map { it to DEFAULT }.toMap().toMutableMap()
    val dealerResult
        get() = _dealerResult.toMap()
    val playersResult = players.players.map { it to getWinOrLose(it) }.toMap()

    private fun getWinOrLose(player: Player): WinOrLose {
        return when {
            checkWinner(player) -> {
                _dealerResult[WinOrLose.LOSE] = _dealerResult.getValue(WinOrLose.LOSE) + 1
                WinOrLose.WIN
            }
            checkDrawPlayer(player) && checkBust(player) ?: true -> {
                _dealerResult[WinOrLose.DRAW] = _dealerResult.getValue(WinOrLose.DRAW) + 1
                WinOrLose.DRAW
            }
            else -> {
                _dealerResult[WinOrLose.WIN] = _dealerResult.getValue(WinOrLose.WIN) + 1
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
        const val DEFAULT = 0
    }
}
