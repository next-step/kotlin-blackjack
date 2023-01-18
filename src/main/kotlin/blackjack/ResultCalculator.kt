package blackjack

import blackjack.domains.participants.Dealer
import blackjack.domains.participants.Gamers
import blackjack.domains.participants.Player

class ResultCalculator(private val users: Gamers) {

    fun setUserRanks() {
        val dealer = users.getDealer()
        val players = users.getPlayers()
        if (dealer.isOver21()) {
            players.forEach { player -> playerWin(dealer, player) }
            return
        }
        players.forEach { player ->
            when (dealer.calculatePlayerResult(player.summedCardNumbers)) {
                GameScoreType.WIN -> playerWin(dealer, player)
                GameScoreType.LOSE -> playerLose(dealer, player)
                GameScoreType.DRAW -> playerDraw(dealer, player)
            }
        }
    }

    private fun playerWin(dealer: Dealer, player: Player) {
        player.win()
        dealer.lose()
    }

    private fun playerDraw(dealer: Dealer, player: Player) {
        player.draw()
        dealer.draw()
    }

    private fun playerLose(dealer: Dealer, player: Player) {
        player.lose()
        dealer.win()
    }
}
