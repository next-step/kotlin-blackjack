package blackjack.domain.result

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Match
import blackjack.domain.participant.Participant

class BlackJackManager(
    private val dealer: Dealer,
    private val participants: List<Participant>
) {

    fun matching(): BlackJackResult {
        val playerResults = getPlayerResults()
        val dealerResult = getDealerResult(playerResults)
        return BlackJackResult(dealerResult, playerResults)
    }

    private fun getDealerResult(playerResults: List<PlayerResult>): DealerResult {
        val win = playerResults.count { it.match == Match.LOSE }
        val draw = playerResults.count { it.match == Match.DRAW }
        val lose = playerResults.count { it.match == Match.WIN }
        return DealerResult(win, draw, lose)
    }

    private fun getPlayerResults(): List<PlayerResult> {
        return participants.map {
            PlayerResult(it.name, it.match(dealer))
        }
    }
}

data class BlackJackResult(
    val dealerResult: DealerResult,
    val playerResults: List<PlayerResult>
)

data class DealerResult(
    val win: Int,
    val draw: Int,
    val lose: Int
)

data class PlayerResult(
    val playerName: String,
    val match: Match
)
