package blackjack

import blackjack.domain.card.Deck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.strategy.draw.DrawStrategy
import blackjack.strategy.draw.HitDrawStrategy
import blackjack.strategy.draw.ReadyDrawStrategy
import blackjack.strategy.shuffle.CardsRandomShuffleStrategy
import blackjack.strategy.split.SingleCommaSplitStrategy
import blackjack.strategy.ui.input.ConsoleInputStrategy
import blackjack.strategy.ui.output.ConsoleOutputStrategy
import blackjack.ui.Command
import blackjack.ui.ErrorView
import blackjack.ui.InputView
import blackjack.ui.ResultView

class BlackJackApplication(
    private val inputView: InputView,
    private val resultView: ResultView,
    private val errorView: ErrorView,
) {
    fun run() {
        val deck = Deck.initialize(CardsRandomShuffleStrategy)
        val dealer = readyDealer(deck, ReadyDrawStrategy)
        val gamePlayer = readyGamePlayer(deck, ReadyDrawStrategy)
        resultView.showReadiedPlayers(dealer, gamePlayer)

        val endedGamePlayer = gamePlayerStartGame(gamePlayer, deck, HitDrawStrategy)
        val endedDealer = dealerStartGame(dealer, deck, HitDrawStrategy)
        resultView.showEndedPlayers(endedDealer, endedGamePlayer)

        resultView.showMatchResult(endedDealer, endedGamePlayer)
    }

    private fun readyDealer(deck: Deck, drawStrategy: DrawStrategy): Player =
        Dealer().draw(deck, drawStrategy)

    private fun readyGamePlayer(deck: Deck, drawStrategy: DrawStrategy): Players =
        players().players
            .map { it.draw(deck, drawStrategy) }
            .let { Players.from(it) }

    private fun players(): Players {
        return try {
            Players.of(inputView.askPlayerInformation(), SingleCommaSplitStrategy)
        } catch (e: Exception) {
            errorView.showErrorMessage(e.message.toString())
            players()
        }
    }

    private fun gamePlayerStartGame(gamePlayer: Players, deck: Deck, drawStrategy: DrawStrategy) =
        gamePlayer.players
            .map { drawOrStay(it, deck, drawStrategy) }
            .let { Players.from(it) }

    private fun drawOrStay(player: Player, deck: Deck, drawStrategy: DrawStrategy): Player {
        return when {
            player.isFinished() -> player
            !askDrawable(player) -> player.stay()
            else -> draw(player, deck, drawStrategy)
        }
    }

    private fun askDrawable(player: Player): Boolean {
        return try {
            val command = Command.values(inputView.askDrawable(player.name.name))
            return command.isDrawable
        } catch (e: Exception) {
            errorView.showErrorMessage(e.message.toString())
            askDrawable(player)
        }
    }

    private fun draw(player: Player, deck: Deck, drawStrategy: DrawStrategy): Player {
        val nowPlayer = player.draw(deck, drawStrategy)
        resultView.showPlayerHands(nowPlayer)
        return drawOrStay(nowPlayer, deck, drawStrategy)
    }

    private fun dealerStartGame(dealer: Player, deck: Deck, hitDrawStrategy: HitDrawStrategy): Player {
        var nowDealer = dealer
        while (!nowDealer.isFinished()) {
            resultView.noticeDealerDraw()
            nowDealer = dealer.draw(deck, hitDrawStrategy)
        }
        return nowDealer
    }
}

fun main() {
    val inputView = InputView(ConsoleInputStrategy, ConsoleOutputStrategy)
    val resultView = ResultView(ConsoleOutputStrategy)
    val errorView = ErrorView(ConsoleOutputStrategy)
    BlackJackApplication(inputView, resultView, errorView).run()
}
