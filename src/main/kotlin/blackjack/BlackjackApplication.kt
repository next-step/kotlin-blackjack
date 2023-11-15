package blackjack

import blackjack.domain.*
import blackjack.view.InputView
import blackjack.view.OutputView

fun main(args: Array<String>) {
    val dealer = Dealer(deck(RandomShuffleStrategy) { Card.ALL_CARDS.map { +it } })

    val nicknames = InputView.readNicknames()
    val players = nicknames.map { Player(it) }

    val game = Game(players, dealer, object : GameObserver {
        override fun onGameStarted(participants: List<Participant>) {
            OutputView.writePlayerNames(participants)
            OutputView.writeParticipantCards(*participants.toTypedArray())
        }

        override fun onPlayerHits(player: Player) {
            OutputView.writeParticipantCards(player)
        }

        override fun onDealerHits(dealer: Dealer) {
            OutputView.writeDealer(dealer)
        }

        override fun onGameEnded(participants: List<Participant>) {
            OutputView.writeParticipantResults(participants)
        }
    })

    game.startGame()
    game.playerTurn { player -> InputView.readHitOrStand(player.name) == PlayerDecision.HIT }
    game.dealerTurn()

    val results = game.getResults()
    OutputView.writeGameResults(results)
}
