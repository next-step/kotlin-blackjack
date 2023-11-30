package blackjack_dealer.entity

import blackjack_dealer.dto.ParticipantBetAmount

class AllParticipantWithBetAmount(
    private val allParticipantWithBetAmount: List<ParticipantNamesWithBetAmount>
) : List<ParticipantNamesWithBetAmount> by allParticipantWithBetAmount {
    companion object {
        fun newInstance(names: String, betAmounts: List<ParticipantBetAmount>): AllParticipantWithBetAmount {
            val participantsName = names.split(',')
            return participantsName.mapIndexed { index, name ->
                ParticipantNamesWithBetAmount(name = name, betAmount = betAmounts[index].getBetAmount())
            }.toAllParticipantWithBetAmount()
        }
    }
}

fun List<ParticipantNamesWithBetAmount>.toAllParticipantWithBetAmount() = AllParticipantWithBetAmount(this)
