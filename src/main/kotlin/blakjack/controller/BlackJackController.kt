package blakjack.controller

import blakjack.domain.Dealer
import blakjack.domain.Game
import blakjack.domain.Participant
import blakjack.domain.Player
import blakjack.view.InputView
import blakjack.view.OutputView

class BlackJackController {

    fun start() {
        val game = Game(
            players = InputView.readPlayerNames().map { Player(name = it) }
        )
        val dealer = game.dealer
        val players = game.players
        val participants = listOf(dealer) + players

        players.forEach { player -> playerBetting(game, player) }
        game.initialDraw()
        printIntro(participants)

        players.forEach { player -> playerTurn(game, player) }
        dealerTurn(game, dealer)

        game.result()
        OutputView.printCardsWithScore(participants)
        OutputView.printResult(dealer, players)
        OutputView.printProfit(dealer, players)
    }

    private fun printIntro(participants: List<Participant>) {
        OutputView.printIntro(participants).also {
            participants.forEach { OutputView.printCards(it) }
        }
    }

    private fun playerBetting(game: Game, player: Player) {
        val bettingMoney = InputView.readBettingMoney(player.name)
        game.bet(player, bettingMoney)
    }

    private fun playerTurn(game: Game, player: Player) {
        while (player.isNotBust() && InputView.readHitOrStand(player.name)) {
            game.hit(player)
            OutputView.printCards(player)
        }
    }

    private fun dealerTurn(game: Game, dealer: Dealer) {
        game.hitOrStandDealer()

        if (dealer.isHit()) {
            OutputView.printDealerHit()
        }
    }
}
