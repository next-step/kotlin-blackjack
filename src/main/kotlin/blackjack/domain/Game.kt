package blackjack.domain

import java.lang.IllegalArgumentException

class Game(val participants: Participants, val dealer: Dealer, private var cards: GameCards = GameCards()) {

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

    fun findWinner() {
        if (dealer.isWinScore()) {
            return
        }

        val winner = findWinnerScore()
        makeWinners(winner)
    }

    private fun findWinnerScore(): Participant {
        return participants.minBy { it.calculateToFindWinner() } ?: throw IllegalArgumentException("무승부입니다.")
    }

    private fun makeWinners(winner: Participant) {
        participants.filter { it.cards.score == winner.cards.score }.forEach { it.win() }
    }

    companion object {
        const val BLACK_JACK_SCORE = 21
        const val BLACK_JACK_CARD_COUNT = 2
        const val START_INDEX = 1
        private const val NO_PLAYING_COUNT = 0
    }
}
