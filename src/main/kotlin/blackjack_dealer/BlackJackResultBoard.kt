package blackjack_dealer

import blackjack_dealer.domain.Dealer
import blackjack_dealer.entity.Participants
import blackjack_dealer.entity.result.DealerResult
import blackjack_dealer.entity.result.ParticipantResult
import blackjack_dealer.entity.result.TotalResult
import blackjack_dealer.entity.state.ParticipantResultState

object BlackJackResultBoard {
    fun getBlackJackResult(dealer: Dealer, participants: Participants): TotalResult {
        val participantsResult = mapParticipantsToParticipantsResult(participants, dealer)

        val groupingByParticipantResult = groupingAndCountParticipantsResult(participantsResult)

        val participantResultCounts = getParticipantResultStateGroup(groupingByParticipantResult)

        val dealerResult = createDealerResult(participantResultCounts)

        return TotalResult(dealerResult = dealerResult, participantsResult = participantsResult)
    }

    private fun mapParticipantsToParticipantsResult(
        participants: Participants,
        dealer: Dealer
    ): List<ParticipantResult> {
        return participants.map { participant ->
            val participantState = ParticipantResultState.of(dealer, participant)
            ParticipantResult(name = participant.getGamerName(), resultState = participantState)
        }
    }

    private fun groupingAndCountParticipantsResult(participantsResult: List<ParticipantResult>) =
        participantsResult
            .groupingBy { it.resultState }
            .eachCount()

    private fun getParticipantResultStateGroup(groupingByParticipantResult: Map<ParticipantResultState, Int>) =
        ParticipantResultState.values()
            .associateWith { groupingByParticipantResult.getOrDefault(it, DEFAULT_VALUE) }

    private fun createDealerResult(participantResultCounts: Map<ParticipantResultState, Int>) =
        DealerResult(
            winCount = participantResultCounts.getOrDefault(ParticipantResultState.LOSE, DEFAULT_VALUE),
            loseCount = participantResultCounts.getOrDefault(ParticipantResultState.WIN, DEFAULT_VALUE),
            drawCount = participantResultCounts.getOrDefault(ParticipantResultState.DRAW, DEFAULT_VALUE)
        )


    private const val DEFAULT_VALUE = 0
}
