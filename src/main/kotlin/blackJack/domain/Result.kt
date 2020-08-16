package blackJack.domain

class Result(private val dealer: Dealer, players: Players) {
    val playersResult = players.players.map { it to getWinOrLose(it) }.toMap()

    private fun getWinOrLose(player: Player): String {
        return when {
            checkWinner(player) -> {
                DealerResult.LOSE.addCount()
                "승"
            }
            checkDrawPlayer(player) && checkBust(player) ?: true -> {
                DealerResult.DRAW.addCount()
                "무"
            }
            else -> {
                DealerResult.WIN.addCount()
                "패"
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
}
