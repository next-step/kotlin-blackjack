package blackjack.domain

import kotlin.math.abs

class BlackJackOutcomeCalculator : GameOutcomeCalculator {

    override fun calculate(dealer: Dealer, players: Players): GameResult {
        val dealerScore = dealer.calculateScore()
        return if (dealerScore > Game.THRESHOLD) {
            GameResult(
                dealerRecord = mapOf(OutcomeType.LOSE to players.size),
                playerRecords = players.associateWith { OutcomeType.WIN }
            )
        } else {
            GameResult(
                dealerRecord = players.groupingBy { recodingOutcome(dealer, it) }
                    .eachCount().mapKeys { it.key.dealerOutcome },
                playerRecords = players.associateWith { recodingOutcome(dealer, it).playerOutcome }
            )
        }
    }

    private fun recodingOutcome(dealer: Dealer, player: Player): OutcomeResultEntry {
        val playerScore = player.calculateScore()

        return when {
            playerScore > Game.THRESHOLD -> PLAYER_WIN_PAIR
            (abs(Game.THRESHOLD - dealer.calculateScore()) < abs(Game.THRESHOLD - playerScore)) -> DEALER_WIN_PAIR
            else -> PLAYER_WIN_PAIR
        }
    }

    companion object {
        private val DEALER_WIN_PAIR =
            OutcomeResultEntry(dealerOutcome = OutcomeType.WIN, playerOutcome = OutcomeType.LOSE)
        private val PLAYER_WIN_PAIR =
            OutcomeResultEntry(dealerOutcome = OutcomeType.LOSE, playerOutcome = OutcomeType.WIN)

        data class OutcomeResultEntry(val dealerOutcome: OutcomeType, val playerOutcome: OutcomeType)
    }
}
