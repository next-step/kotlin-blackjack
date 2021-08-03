package blackjack.domain

class Game(val participants: Participants, val dealer: Dealer, private var cards: GameCards = GameCards()) {

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
        participants.filter { it.retrieveScore() == winner.retrieveScore() }.forEach { it.win() }
    }

    companion object {
        const val BLACK_JACK_SCORE = 21
        const val BLACK_JACK_CARD_COUNT = 2
        const val START_INDEX = 1
    }
}
