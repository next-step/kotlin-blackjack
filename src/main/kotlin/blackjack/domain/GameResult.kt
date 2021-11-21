package blackjack.domain

import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Gamers
import blackjack.domain.gamer.Player

class GameResult(
    private val gamers: Gamers,
) {

    fun calculatePlayersResult(): List<Player> {
        val dealer = gamers.getDealer()
        val players = gamers.getPlayers()
        return players.map { calculatePlayerResult(it, dealer) }
    }

    private fun calculatePlayerResult(player: Player, dealer: Dealer): Player {
        val dealerScore = dealer.currentScore()
        val playerScore = player.currentScore()
        if (dealerScore == playerScore) {
            return player.push()
        }
        if (player.isBlackjack() || player.isTwentyOne()) {
            return player.win()
        }
        if (dealer.isBust() && !player.isBust()) {
            return player.win()
        }
        if (dealerScore < playerScore) {
            return player.win()
        }
        return player.lose()
    }
}
