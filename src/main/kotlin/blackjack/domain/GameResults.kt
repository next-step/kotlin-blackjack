package blackjack.domain

import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant

object GameResults {
    fun results(dealer: Dealer, participants: List<Participant>): Map<Participant, GameResult> {
        val results = mutableMapOf<Participant, GameResult>()

        participants.forEach { participant ->
            val dealerGameResult = dealer.compareWith(participant)
            results[participant] = when (dealerGameResult) {
                GameResult.WIN -> GameResult.LOSE
                GameResult.LOSE -> GameResult.WIN
                GameResult.TIE -> GameResult.TIE
            }
        }

        return results
    }
}