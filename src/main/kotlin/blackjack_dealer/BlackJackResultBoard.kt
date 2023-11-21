package blackjack_dealer

import blackjack_dealer.domain.Dealer
import blackjack_dealer.entity.Participants
import blackjack_dealer.entity.result.DealerResult
import blackjack_dealer.entity.result.ParticipantResult
import blackjack_dealer.entity.result.TotalResult
import blackjack_dealer.entity.state.ParticipantResultState

object BlackJackResultBoard {
    fun getBlackJackResult(dealer: Dealer, participants: Participants): TotalResult {
        val participantsResult = ParticipantResult.createParticipantResults(participants, dealer)

        val groupingByParticipantResult = ParticipantResult.groupingAndCountParticipantsResult(participantsResult)

        val participantResultCounts = ParticipantResultState.getParticipantResultStateGroup(groupingByParticipantResult)

        val dealerResult = DealerResult.createDealerResultFromParticipantResult(participantResultCounts)

        return TotalResult(dealerResult = dealerResult, participantsResult = participantsResult)
    }
}
