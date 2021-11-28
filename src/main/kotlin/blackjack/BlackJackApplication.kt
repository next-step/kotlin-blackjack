package blackjack

import blackjack.domain.bet.Bets
import blackjack.domain.bet.Money
import blackjack.domain.card.Deck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.player.name.Name
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
        var players = players()
        var dealer = Dealer()
        val bets = bettingBoard(players)

        val readiedPlayers = readiedPlayers(players, deck, ReadyDrawStrategy)
        val readiedDealer = readiedDealer(dealer, deck, ReadyDrawStrategy)
        resultView.showReadiedPlayers(readiedDealer, readiedPlayers)

        val endedPlayers = startGameOnGamePlayer(readiedPlayers, deck, HitDrawStrategy)
        val endedDealer = startGameOneDealer(readiedDealer, deck, HitDrawStrategy)
        resultView.showEndedPlayers(endedDealer, endedPlayers)

        resultView.showProfitResult(endedDealer, endedPlayers, bets)
    }

    private fun players(): Players =
        try {
            Players.of(inputView.askPlayersInformation(), SingleCommaSplitStrategy)
        } catch (e: Exception) {
            errorView.showErrorMessage(e.message.toString())
            players()
        }

    private fun bettingBoard(players: Players): Bets {
        return Bets.of(
            players.players
                .map { it.name }
                .associateWith { betMoney(it) }
        )
    }

    private fun betMoney(name: Name): Money =
        try {
            Money(inputView.askPlayersBetMoney(name.name))
        } catch (e: Exception) {
            errorView.showErrorMessage(e.message.toString())
            betMoney(name)
        }

    private fun readiedDealer(dealer: Dealer, deck: Deck, drawStrategy: ReadyDrawStrategy): Player =
        dealer.draw(deck, drawStrategy)

    private fun readiedPlayers(players: Players, deck: Deck, drawStrategy: ReadyDrawStrategy): Players =
        players.players
            .map { it.draw(deck, drawStrategy) }
            .let { Players.from(it) }

    private fun startGameOnGamePlayer(players: Players, deck: Deck, drawStrategy: DrawStrategy): Players =
        players.players
            .map { drawOrStay(it, deck, drawStrategy) }
            .let { Players.from(it) }

    private fun drawOrStay(player: Player, deck: Deck, drawStrategy: DrawStrategy): Player =
        when {
            player.isFinished() -> player
            !askDrawable(player) -> player.stay()
            else -> draw(player, deck, drawStrategy)
        }

    private fun askDrawable(player: Player): Boolean =
        try {
            val command = Command.values(inputView.askDrawable(player.name.name))
            command.isDrawable
        } catch (e: Exception) {
            errorView.showErrorMessage(e.message.toString())
            askDrawable(player)
        }

    private fun draw(player: Player, deck: Deck, drawStrategy: DrawStrategy): Player {
        val nowPlayer = player.draw(deck, drawStrategy)
        resultView.showPlayerHands(nowPlayer)
        return drawOrStay(nowPlayer, deck, drawStrategy)
    }

    private fun startGameOneDealer(dealer: Player, deck: Deck, hitDrawStrategy: HitDrawStrategy): Player {
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
