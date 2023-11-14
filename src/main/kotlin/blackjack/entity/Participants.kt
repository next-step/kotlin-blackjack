package blackjack.entity

data class Participants(
    val participants: List<Participant>
) : List<Participant> by participants
