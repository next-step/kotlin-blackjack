package service

import domain.BlackjackCards
import domain.Dealer
import domain.Player

class BlackjackWinnerService {
    fun winner(
        players: Set<Player>,
        dealer: Dealer,
    ): Map<Player, Boolean> {
        val result = mutableMapOf<Player, Boolean>()

        players.forEach {
            if (dealer.score() > BlackjackCards.BLACKJACK_MAX_SCORE) {
                result[it] = true
            } else if (it.score() > BlackjackCards.BLACKJACK_MAX_SCORE) {
                result[it] = false
            } else {
                result[it] = it.score() >= dealer.score()
            }
        }

        return result
    }
}
