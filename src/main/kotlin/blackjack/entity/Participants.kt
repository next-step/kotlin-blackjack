package blackjack.entity

import blackjack.domain.CardGenerator

data class Participants(
    val participants: List<Participant>
) : List<Participant> by participants

fun String.participantsFromNames() = this.split(", ").map { name ->
    Participant(name = name, cards = CardGenerator.generateCard(INITIAL_COUNT_OF_CARD))
}.toParticipants()

private const val INITIAL_COUNT_OF_CARD = 2

fun List<Participant>.toParticipants() = Participants(this)
