package blackjack.application

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Match
import blackjack.domain.participant.Participant
import blackjack.domain.result.BlackJackResult
import blackjack.domain.result.DealerResult
import blackjack.domain.result.PlayerResult

class BlackJack(
    val dealer: Dealer,
    val participants: List<Participant>
) {

    init {
        require(participants.size in PLAYER_RANGE) {
            "블랙잭을 진행하기 위한 인원은 ${PLAYER_RANGE.first} ~ ${PLAYER_RANGE.last} 명입니다. 현재 인원 = ${participants.size}"
        }
    }

    fun distribute() {
        participants.forEach { participant ->
            participant.receive(dealer.draw())
            participant.receive(dealer.draw())
        }
        dealer.receive(dealer.draw())
        dealer.receive(dealer.draw())
    }

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

    companion object {
        private val PLAYER_RANGE = 2..6
    }
}
