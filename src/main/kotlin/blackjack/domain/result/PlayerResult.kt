package blackjack.domain.result

import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Gamers
import blackjack.domain.gamer.Player

class PlayerResult(
    private val gamers: Gamers,
) {
    val value = gamers.value

    fun calculatePlayersResult(): List<Player> {
        val dealer = gamers.getDealer()
        val players = gamers.getPlayers()
        return players.map { calculatePlayerResult(it, dealer) }
    }

    private fun calculatePlayerResult(player: Player, dealer: Dealer): Player {
        val dealerScore = dealer.currentScore()
        val playerScore = player.currentScore()
        return when {
            (dealerScore == playerScore) -> player.push()
            (player.isBlackjack() || player.isTwentyOne()) -> player.win()
            (player.isBust()) -> player.lose()
            (dealer.isBust() && !player.isBust()) -> player.win()
            (dealerScore < playerScore) -> player.win()
            else -> player.lose()
        }
    }
}
