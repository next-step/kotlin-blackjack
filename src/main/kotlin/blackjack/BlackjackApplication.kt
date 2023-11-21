package blackjack

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.domain.RandomShuffleStrategy
import blackjack.domain.deck
import blackjack.view.InputView
import blackjack.view.OutputView

fun main(args: Array<String>) {
    val players = InputView.readNicknames().map { Player(it) }
    val dealer = Dealer(deck(RandomShuffleStrategy) { Card.ALL_CARDS.map { +it } })

    val game = Game(players, dealer)
    game.startGame()

    OutputView.writePlayerNames(game.participants)
    OutputView.writeParticipantCards(game.participants)

    players.forEach { player ->
        while (game.playerTurn(player, InputView.readHitOrStand(player.name))) {
            OutputView.writeParticipantCards(listOf(player))
        }
    }

    if (game.dealerTurn()) {
        OutputView.writeDealer(dealer)
    }

    val results = game.getResults()
    OutputView.writeGameResults(game.participants, results)
}
