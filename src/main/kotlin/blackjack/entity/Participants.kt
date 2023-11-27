package blackjack.entity

import blackjack.domain.CardGenerator
import blackjack.domain.Participant

data class Participants(
    val participants: List<Participant>
) : List<Participant> by participants

fun String.participantsFromNames(cardDeque: ParticipantCards) = this.split(DELIMITER).map { name ->
    Participant(name = name, participantCards = CardGenerator.generateCard(INITIAL_COUNT_OF_CARD, cardDeque))
}.toParticipants()

private const val INITIAL_COUNT_OF_CARD = 2
private const val DELIMITER = ", "

fun List<Participant>.toParticipants() = Participants(this)