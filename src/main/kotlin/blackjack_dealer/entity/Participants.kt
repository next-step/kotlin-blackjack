package blackjack_dealer.entity

import blackjack_dealer.domain.Dealer
import blackjack_dealer.domain.Participant

data class Participants(
    val participants: List<Participant>
) : List<Participant> by participants {

    fun getParticipantsCanPlayGame(): Participants = participants.filter { it.canKeepPlayingGame() }.toParticipants()
    fun getParticipantsLoss(dealer: Dealer): Int = participants.sumOf { it.getResultBetAmount(dealer) } * -1

    companion object {
        fun newInstance(
            allParticipantWithBetAmount: AllParticipantWithBetAmount,
            cardDeque: () -> GamerCards
        ): Participants {
            return allParticipantWithBetAmount.map { participantWithBetAmount ->
                Participant.newInstance(
                    name = participantWithBetAmount.name,
                    cards = cardDeque.invoke(),
                    betAmount = participantWithBetAmount.betAmount
                )
            }.toParticipants()
        }
    }
}

fun List<Participant>.toParticipants() = Participants(this)
