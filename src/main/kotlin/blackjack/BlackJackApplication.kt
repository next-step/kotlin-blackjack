package blackjack

import blackjack.domain.card.Deck
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.strategy.assign.FirstAssignCardStrategy
import blackjack.strategy.assign.HitAssignCardStrategy
import blackjack.strategy.shuffle.DeckRandomShuffleStrategy
import blackjack.strategy.split.CommaSplitStrategy
import blackjack.strategy.ui.input.ConsoleInputStrategy
import blackjack.strategy.ui.output.ConsoleOutputStrategy
import blackjack.ui.ErrorView
import blackjack.ui.InputView
import blackjack.ui.ResultView

class BlackJackApplication(
    private val inputView: InputView,
    private val resultView: ResultView,
    private val errorView: ErrorView,
) {
    fun run() {
        val deck = Deck.initialize(DeckRandomShuffleStrategy)
        var players = inputParticipantsInformation().assignCards(deck, FirstAssignCardStrategy)
        resultView.noticeAssignCard(players)
        players = players.players
            .map { player -> startGame(player, deck) }
            .let { Players.from(it) }
        resultView.showResult(players)
    }

    private fun startGame(previousPlayer: Player, deck: Deck): Player {
        return try {
            if (previousPlayer.isFinished()) {
                return previousPlayer
            }
            val command = inputView.inputWhetherAdditionalCardAcquisition(previousPlayer.name)
            var nowPlayer = previousPlayer.continuePlay(command)
            if (nowPlayer.isFinished()) {
                return nowPlayer
            }
            val playingCard = HitAssignCardStrategy.assign(deck)
            nowPlayer += playingCard
            resultView.showPlayerHands(nowPlayer)
            startGame(nowPlayer, deck)
        } catch (e: Exception) {
            errorView.showErrorMessage(e.message.toString())
            startGame(previousPlayer, deck)
        }
    }

    private fun inputParticipantsInformation(): Players {
        return try {
            Players.of(inputView.inputParticipantsInformation(), CommaSplitStrategy)
        } catch (e: Exception) {
            errorView.showErrorMessage(e.message.toString())
            inputParticipantsInformation()
        }
    }
}

fun main() {
    val inputView = InputView(ConsoleInputStrategy, ConsoleOutputStrategy)
    val resultView = ResultView(ConsoleOutputStrategy)
    val errorView = ErrorView(ConsoleOutputStrategy)
    BlackJackApplication(inputView, resultView, errorView).run()
}
