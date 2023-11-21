package blackjack_dealer

import blackjack_dealer.domain.Dealer
import blackjack_dealer.entity.Participants
import blackjack_dealer.entity.result.DealerResult
import blackjack_dealer.entity.result.ParticipantResult
import blackjack_dealer.entity.result.TotalResult
import blackjack_dealer.entity.state.ParticipantResultState

object BlackJackResultBoard {
    fun getBlackJackResult(dealer: Dealer, participants: Participants): TotalResult {
        val participantsResult = participants.map { participant ->
            val participantState = ParticipantResultState.of(dealer, participant)
            ParticipantResult(name = participant.getParticipantName(), resultState = participantState)
        }

        val groupingByParticipantResult = participantsResult
            .groupingBy { it.resultState }
            .eachCount()

        val participantResultCounts =
            ParticipantResultState.values().associateWith { groupingByParticipantResult.getOrDefault(it, 0) }

        val dealerResult = DealerResult(
            winCount = participantResultCounts.getOrDefault(ParticipantResultState.LOSE, 0),
            loseCount = participantResultCounts.getOrDefault(ParticipantResultState.WIN, 0),
            drawCount = participantResultCounts.getOrDefault(ParticipantResultState.DRAW, 0)
        )

        return TotalResult(dealerResult = dealerResult, participantsResult = participantsResult)
    }
}
