package blackjack.controller

import blackjack.domain.Game
import blackjack.domain.Names
import blackjack.domain.Participant
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val names = Names(InputView.playerNames())

    val game = Game(names)

    ResultView.printInitNotice(names, Game.BLACK_JACK_CARD_COUNT)

    ResultView.printAllPlayerCards(game)

    playGameWithPlayers(game)

    ResultView.printAllResult(game)
}

private fun playGameWithPlayers(game: Game) {
    game.participants.forEach {
        playGame(it, game)
    }
}

private fun playGame(participant: Participant, game: Game) {
    while (participant.isPlaying) {

        if (participant.isEnd) {
            break
        }

        drawIfSmallerThanMinimum(participant, game)

        val answer = InputView.askIfPlayerWantToMoreCard(participant.name)

        drawOfStopByAnswer(answer, game, participant)

        ResultView.printPlayerCards(participant.name, participant.cards)
    }
}

private fun drawIfSmallerThanMinimum(participant: Participant, game: Game) {
    if (participant.isSmallerThanMinimumScore()) {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        game.draw(participant)
    }
}

private fun drawOfStopByAnswer(answer: Boolean, game: Game, it: Participant) {
    if (answer) {
        game.draw(it)
        return
    }

    it.stop()
}
