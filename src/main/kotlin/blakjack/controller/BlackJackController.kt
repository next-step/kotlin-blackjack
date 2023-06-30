package blakjack.controller

import blakjack.domain.Game
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
        OutputView.printIntro(participants).also {
            participants.forEach { OutputView.printCards(it) }
        }

        players.forEach { player ->
            while (InputView.readHitOrStand(player.name)) {
                game.hit(player)
                OutputView.printCards(player)
                if (player.isBust()) {
                    break
                }
            }
        }

        val dealerAction = game.hitOrStandDealer()
        if (dealerAction == ParticipantAction.HIT) {
            OutputView.printDealerHit()
        }

        OutputView.printCardsWithScore(participants)
    }
}
