package blackjack.presentation

import blackjack.domain.deck.Deck
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Gamer
import blackjack.domain.gamer.Gamers
import blackjack.domain.gamer.Player
import blackjack.domain.result.DealerResult
import blackjack.domain.result.PlayerResult
import blackjack.domain.state.State.Companion.CAN_PLAY
import blackjack.domain.state.State.Companion.FINISHED_SIGN
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val deck: Deck = Deck.init(),
) {
    fun run() {
        val gamers = initGamers()
        val preparedGamers = gamers.prepare(deck)
        OutputView.printStartGame(preparedGamers.value)

        val playedBlackjack = play(preparedGamers)
        val playerResult = PlayerResult(playedBlackjack)

        val playersResult = playerResult.calculatePlayersResult()
        val dealerResult = DealerResult.calculateDealerResult(playersResult)
        OutputView.printFinalOutcome(dealerResult, playersResult)
    }

    private fun initGamers(): Gamers {
        val inputPlayerNames = InputView.inputPlayers()
        return Gamers.init(inputPlayerNames)
    }

    private fun play(preparedGamers: Gamers): Gamers {
        val playPlayersResult = playPlayers(preparedGamers.getPlayers())
        val playDealerResult = playDealer(preparedGamers.getDealer())
        val blackjackResult = listOf(playDealerResult) + playPlayersResult
        OutputView.printBlackjackResult(blackjackResult)
        return Gamers.from(blackjackResult)
    }

    private fun playPlayers(players: List<Player>): List<Gamer> {
        val completedBlackjackPlayers = mutableListOf<Gamer>()

        for (player in players) {
            while (true) {
                val playable = inputPlayable(player)

                if (player.isStand(playable)) {
                    player.stand()
                    OutputView.printGamerCard(player)
                    completedBlackjackPlayers.add(player)
                    break
                }
                val progressedBlackjack = player.play(deck)
                println()
                OutputView.printGamerCard(player)

                if (progressedBlackjack.isFinished()) {
                    completedBlackjackPlayers.add(progressedBlackjack)
                    break
                }
            }
        }
        return completedBlackjackPlayers
    }

    private fun playDealer(dealer: Dealer): Gamer {
        val preparedCurrentScore = dealer.currentScore()
        OutputView.printCurrentDealerScore(preparedCurrentScore)
        while (true) {
            val currentScore = dealer.currentScore()
            if (currentScore >= DEALER_DRAW_CONDITION) {
                return dealer.meetConditions(currentScore)
            }
            val progressedBlackjack = dealer.play(deck)
            println()
            OutputView.printGamerCard(dealer)

            if (progressedBlackjack.isFinished()) {
                return progressedBlackjack
            }
        }
    }

    private fun inputPlayable(player: Gamer): Boolean {
        if (player.isFinished()) {
            return false
        }
        val playable = InputView.inputCardSign(player)
        if (playable == FINISHED_SIGN) {
            return false
        }
        return playable == CAN_PLAY
    }

    companion object {
        private const val DEALER_DRAW_CONDITION = 17
    }
}
