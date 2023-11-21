package blackjack

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.domain.PlayerDecision
import blackjack.domain.RandomShuffleStrategy
import blackjack.domain.calculateScore
import blackjack.domain.deck
import blackjack.view.InputView
import blackjack.view.OutputView

fun main(args: Array<String>) {
    val dealer = Dealer(deck(RandomShuffleStrategy) { Card.ALL_CARDS.map { +it } })

    val nicknames = InputView.readNicknames()
    val players = nicknames.map { Player(it) }

    val game = Game(players, dealer)

    game.startGame()
    OutputView.writePlayerNames(game.participants)
    OutputView.writeParticipantCards(game.participants)

    players.forEach { player ->
        while (player.canHit() && InputView.readHitOrStand(player.name) == PlayerDecision.HIT) {
            player.receiveCard(dealer.dealCard())
            OutputView.writeParticipantCards(listOf(player))
        }
    }

    if (dealer.calculateScore() <= 16) {
        dealer.receiveCard(dealer.dealCard())
        OutputView.writeDealer(dealer)
    }

    OutputView.writeParticipantResults(game.participants)

    val results = game.getResults()
    OutputView.writeGameResults(results)
}
