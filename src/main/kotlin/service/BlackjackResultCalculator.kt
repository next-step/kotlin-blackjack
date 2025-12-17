package service

import domain.BlackjackCards
import domain.Dealer
import domain.GameOutcome
import domain.GameOutcome.LOSE
import domain.GameOutcome.WIN
import domain.Player

class BlackjackResultCalculator {
    fun determineWinStatus(
        players: Set<Player>,
        dealer: Dealer,
    ): Map<Player, GameOutcome> {
        val result = mutableMapOf<Player, GameOutcome>()

        players.forEach {
            if (dealer.score() > BlackjackCards.BLACKJACK_MAX_SCORE) {
                result[it] = WIN
            } else if (it.score() > BlackjackCards.BLACKJACK_MAX_SCORE) {
                result[it] = LOSE
            } else {
                result[it] = GameOutcome.judge(it.score() >= dealer.score())
            }
        }

        return result
    }
}
