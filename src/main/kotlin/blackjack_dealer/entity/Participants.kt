package blackjack_dealer.entity

import blackjack_dealer.domain.Participant

data class Participants(
    val participants: List<Participant>
) : List<Participant> by participants {
    companion object {
        fun newInstance(nameString: String): Participants {
            val names = nameString.split(',')
            return names.map { name ->
                Participant.newInstance(name)
            }.toParticipants()
        }
    }
}

fun List<Participant>.toParticipants() = Participants(this)
