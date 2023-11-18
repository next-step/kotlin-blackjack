package blackjack.entity

import blackjack.domain.CardGenerator

data class Participants(
    val participants: List<Participant>
) : List<Participant> by participants

fun String.participantsFromNames(cardDeque: Cards) = this.split(DELIMITER).map { name ->
    Participant(name = name, cards = CardGenerator.generateCard(INITIAL_COUNT_OF_CARD, cardDeque))
}.toParticipants()

private const val INITIAL_COUNT_OF_CARD = 2
private const val DELIMITER = ", "

fun List<Participant>.toParticipants() = Participants(this)
