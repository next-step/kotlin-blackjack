package blackjack.model

object Referee {
    private const val BLACK_JACK_SCORE: Int = 21
    fun isBlackJack(it: Player): Boolean {
        return it.cards.totalScore() == BLACK_JACK_SCORE
    }

    fun blackJackResult(participants: Participants): ParticipantResults {
        TODO("Not yet implemented")
    }
}
