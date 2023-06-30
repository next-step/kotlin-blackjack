package blakjack.controller

import blakjack.domain.Game
import blakjack.domain.Participant
import blakjack.domain.Participant.ParticipantAction
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

        game.initialDraw()
        printIntro(participants)

        players.forEach { player -> playerTurn(game, player) }
        dealerTurn(game)

        game.result()
        OutputView.printCardsWithScore(participants)
        OutputView.printResult(dealer, players)
    }

    private fun printIntro(participants: List<Participant>) {
        OutputView.printIntro(participants).also {
            participants.forEach { OutputView.printCards(it) }
        }
    }

    private fun playerTurn(game: Game, player: Player) {
        while (!player.isBust() && InputView.readHitOrStand(player.name)) {
            game.hit(player)
            OutputView.printCards(player)
        }
    }

    private fun dealerTurn(game: Game) {
        val dealerAction = game.hitOrStandDealer()
        if (dealerAction == ParticipantAction.HIT) {
            OutputView.printDealerHit()
        }
    }
}
