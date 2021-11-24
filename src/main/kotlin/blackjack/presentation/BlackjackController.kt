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

        val playedBlackjackGamers = play(preparedGamers)

        val playerResult = PlayerResult(playedBlackjackGamers)
        val judgedPlayerResult = playerResult.judgePlayerResult()
        val dealerResult = DealerResult.from(judgedPlayerResult)

        OutputView.printFinalOutcome(dealerResult, judgedPlayerResult)
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
                val progressedBlackjackPlayer = player.progress(playable, deck)

                OutputView.printGamerCard(progressedBlackjackPlayer)
                if (progressedBlackjackPlayer.isStand()) {
                    completedBlackjackPlayers.add(player)
                    break
                }
                if (progressedBlackjackPlayer.isFinished()) {
                    completedBlackjackPlayers.add(progressedBlackjackPlayer)
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
            val progressedBlackjackDealer = dealer.progress(currentScore, deck)

            println()
            if (progressedBlackjackDealer.isFinished()) {
                return progressedBlackjackDealer
            }
            OutputView.printGamerCard(progressedBlackjackDealer)
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
}
