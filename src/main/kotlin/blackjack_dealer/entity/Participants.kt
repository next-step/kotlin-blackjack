package blackjack_dealer.entity

import blackjack_dealer.domain.Participant
import blackjack_dealer.entity.card.Card

data class Participants(
    val participants: List<Participant>
) : List<Participant> by participants {

    fun getParticipantsCanPlayGame(): Participants = participants.filter { it.canKeepPlayingGame() }.toParticipants()
    companion object {
        fun newInstance(nameString: String, cardDeque: () -> List<Card>): Participants {
            val names = nameString.split(',')
            return names.map { name ->
                Participant.newInstance(name = name, cards = GamerCards(cardDeque().toMutableList()))
            }.toParticipants()
        }
    }
}

fun List<Participant>.toParticipants() = Participants(this)
