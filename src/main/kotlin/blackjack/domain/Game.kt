package blackjack.domain

class Game(val participants: Participants, private val dealer: Participant, private var cards: GameCards = GameCards()) {

    val state: GameStates
        get() {
            if (isEndState) {
                return GameStates.END
            }

            return GameStates.PLAYING
        }

    private val isEndState
        get() = participants.countOfPlayingState == NO_PLAYING_COUNT && dealer.isEnd

    fun draw(participant: Participant) {
        participant.throwExceptionIfIsNotPlayingState()

        val card = cards.poll()
        participant.draw(card)
    }

    fun findWinner() {
        if (dealer.cards.score > BLACK_JACK_SCORE) {
            return
        }

        val winner = participants.minBy { it.calculateToFindWinner() }!!

        participants.filter {
            it.cards.score == winner.cards.score
        }.forEach {
            it.win()
        }
    }

    companion object {
        const val BLACK_JACK_SCORE = 21
        const val BLACK_JACK_CARD_COUNT = 2
        const val START_INDEX = 1
        private const val NO_PLAYING_COUNT = 0
    }
}
