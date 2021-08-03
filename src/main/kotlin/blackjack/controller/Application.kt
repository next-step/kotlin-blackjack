package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Game
import blackjack.domain.GameCards
import blackjack.domain.Names
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val names = Names(InputView.playerNames())

    val gameCards = GameCards()

    val players = Player.generatePlayers(names, gameCards)
    val dealer = Dealer.generateDealer(gameCards)

    val game = Game(players, dealer, gameCards)

    ResultView.printInitNotice(names, Game.BLACK_JACK_CARD_COUNT)

    ResultView.printAllPlayerCards(game)

    game.playGameWithParticipants()

    ResultView.printAllResult(game)

    ResultView.printGameResultTitle()

    game.findWinner()

    ResultView.printGameResults(dealer, game)
}


