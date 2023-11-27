package blackjack.model

class ResultValue(val holder: CardHolder, val winLoseDraw: WinLoseDraw)
class GameResult(private val dealer: CardHolder.GameDealer, private val players: Players) {
    private val result =
        listOf(dealer, *players.playerList.toTypedArray())
            .associate {
                it.id to ResultValue(it, WinLoseDraw())
            }

    fun resolveGame(): Map<Int, ResultValue> {
        players.playerList.forEach(::evaluateResult)
        return result
    }

    private fun evaluateResult(player: CardHolder.Player) {
        when {
            player.isBust -> dealerWin(player)
            dealer.isBust -> playerWin(player)
            player.cardHand.totalScore > dealer.cardHand.totalScore -> playerWin(player)
            player.cardHand.totalScore < dealer.cardHand.totalScore -> dealerWin(player)
            else -> draw(player)
        }
    }

    private fun playerWin(player: CardHolder.Player) {
        addWin(player.id)
        addLose(dealer.id)
    }

    private fun dealerWin(player: CardHolder.Player) {
        addWin(dealer.id)
        addLose(player.id)
    }

    private fun draw(player: CardHolder.Player) {
        addDraw(dealer.id)
        addDraw(player.id)
    }

    private fun addWin(id: Int) {
        result[id]?.winLoseDraw?.win = (result[id]?.winLoseDraw?.win ?: 0) + 1
    }

    private fun addLose(id: Int) {
        result[id]?.winLoseDraw?.lose = (result[id]?.winLoseDraw?.lose ?: 0) + 1
    }

    private fun addDraw(id: Int) {
        result[id]?.winLoseDraw?.draw = (result[id]?.winLoseDraw?.draw ?: 0) + 1
    }
}
