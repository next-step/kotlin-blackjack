package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Game
import blackjack.domain.GameCards
import blackjack.domain.Names
import blackjack.domain.Participant
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val names = Names(InputView.playerNames())

    val gameCards = GameCards()

    val players = Player.generatePlayers(names, gameCards)
    val dealer = Dealer.generateDealer(gameCards)

    val game = Game(players, dealer)

    ResultView.printInitNotice(names, Game.BLACK_JACK_CARD_COUNT)

    ResultView.printAllPlayerCards(game)

    playGameWithPlayers(game)

    ResultView.printAllResult(game)

    ResultView.printGameResultTitle()

    game.findWinner()

    ResultView.printGameResults(dealer, game)
}

private fun playGameWithPlayers(game: Game) {
    game.participants.forEach {
        playGame(it, game)
    }

    playGame(game.dealer, game)
}

private fun playGame(participant: Participant, game: Game) {
    while (participant.isPlaying) {

        drawIfSmallerThanMinimum(participant, game)

        if (participant.isEnd) {
            break
        }

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
