package blackjack.domain.participant

fun interface ScoreStrategy<T : Participant> {
    fun compare(participant: T)
}
