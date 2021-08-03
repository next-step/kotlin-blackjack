package blackjack.domain

import blackjack.view.InputView
import blackjack.view.ResultView

class Game(val participants: Participants, val dealer: Dealer, private val cards: GameCards = GameCards()) {

    fun draw(participant: Participant) {
        participant.throwExceptionIfIsNotPlayingState()

        val card = cards.poll()
        participant.draw(card)
    }

    fun assignWinner() {
        if (dealer.isWinScore()) {
            return
        }

        val winner = participants.findWinnerScore()
        participants.makeWinners(winner)
    }

    fun playGameWithParticipants() {
        playGames()

        drawIfSmallerThanMinimum()
    }

    private fun playGames() {
        participants.forEach {
            playGame(it)
        }
    }

    private fun playGame(participant: Participant) {
        while (participant.isPlaying) {
            val answer = InputView.askIfPlayerWantToMoreCard(participant.name)

            drawOrStopByAnswer(participant, answer)

            ResultView.printPlayerCards(participant.name, participant.cards)
        }
    }

    private fun drawOrStopByAnswer(participant: Participant, answer: Boolean) {
        if (answer) {
            draw(participant)
            return
        }

        participant.stop()
    }

    private fun drawIfSmallerThanMinimum() {
        if (dealer.isSmallerThanMinimumScore()) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
            draw(dealer)
        }
    }

    companion object {
        const val BLACK_JACK_SCORE = 21
        const val BLACK_JACK_CARD_COUNT = 2
        const val START_INDEX = 1
    }
}
