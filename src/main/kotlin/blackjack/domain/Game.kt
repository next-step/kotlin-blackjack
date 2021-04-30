package blackjack.domain

class Game(names: Names, private var cards: GameCards = GameCards()) {
    val participants: Participants = Participants(generateParticipants(names))

    val state: GameStates
        get() {
            if (isEndState) {
                return GameStates.END
            }
            return GameStates.PLAYING
        }

    private val isEndState
        get() = participants.countOfPlayingState == NO_PLAYING_COUNT

    fun draw(participant: Participant) {
        participant.throwExceptionIfIsNotPlayingState()

        val card = cards.poll()
        participant.draw(card)
    }

    private fun generateParticipants(names: Names): Set<Participant> {
        val players = names.map { Player(it, pollCardsToFirstDraw()) }.toSet()

        return players.plusElement(Dealer(pollCardsToFirstDraw()))
    }

    private fun pollCardsToFirstDraw() = (START_INDEX..BLACK_JACK_CARD_COUNT).map { cards.poll() }.toSet()

    companion object {
        const val BLACK_JACK_SCORE = 21
        const val BLACK_JACK_CARD_COUNT = 2
        const val START_INDEX = 1
        private const val NO_PLAYING_COUNT = 0
    }
}
