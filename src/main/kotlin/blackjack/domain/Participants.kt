package blackjack.domain

class Participants(private val participants: Set<Participant>) : Set<Participant> by participants {

    val countOfPlayingState: Int
        get() = participants.count { it.isPlaying }
}
