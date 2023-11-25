package blackjack_dealer.entity

class AllParticipantWithBetAmount(
    private val allParticipantWithBetAmount: List<ParticipantNamesWithBetAmount>
) : List<ParticipantNamesWithBetAmount> by allParticipantWithBetAmount {
    companion object {
        fun newInstance(names: String, betAmounts: List<Int>): AllParticipantWithBetAmount {
            val participantsName = names.split(',')
            return participantsName.mapIndexed { index, name ->
                ParticipantNamesWithBetAmount(name = name, betAmount = betAmounts[index])
            }.toAllParticipantWithBetAmount()
        }
    }
}

fun List<ParticipantNamesWithBetAmount>.toAllParticipantWithBetAmount() = AllParticipantWithBetAmount(this)
