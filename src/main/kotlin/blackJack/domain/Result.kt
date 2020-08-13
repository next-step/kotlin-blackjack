package blackJack.domain

class Result(private val dealer: Dealer) {
    private val _dealerResult = mutableMapOf("승" to 0, "무" to 0, "패" to 0)
    val dealerResult: Map<String, Int>
        get() = _dealerResult.toMap()

    fun get(player: Player): String {
        return when {
            checkWinner(player) -> {
                _dealerResult["패"] = _dealerResult.getValue("패") + 1
                "승"
            }
            checkDrawPlayer(player) && checkBust(player) ?: true -> {
                _dealerResult["무"] = _dealerResult.getValue("무") + 1
                "무"
            }
            else -> {
                _dealerResult["승"] = _dealerResult.getValue("승") + 1
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
