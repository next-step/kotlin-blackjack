package service

import domain.BackjackCards
import domain.Dealer
import domain.Player

class BlackjackWinnerService {
    fun winner(players: Set<Player>, dealer: Dealer): Map<Player, Boolean> {
        val result = mutableMapOf<Player, Boolean>()

        players.forEach { player ->
            if (dealer.score() > BackjackCards.BLACKJACK_MAX_SCORE) {
                result[player] = true
            } else if (player.score() > BackjackCards.BLACKJACK_MAX_SCORE) {
                result[player] = false
            } else {
                result[player] = player.score() >= dealer.score()
            }
        }

        return result
    }
}