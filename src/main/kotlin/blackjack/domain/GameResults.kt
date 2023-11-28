package blackjack.domain

import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant

object GameResults {
    fun results(dealer: Dealer, participants: List<Participant>): Map<Participant, GameResult> {
        val results = mutableMapOf<Participant, GameResult>()

        participants.map { results[it] = it.compareWith(dealer) }

        return results
    }
}