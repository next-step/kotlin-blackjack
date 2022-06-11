package blackjack

import blackjack.dealer.Dealer
import blackjack.player.Players
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.input.ReadInInput
import blackjack.view.output.PrintLnOutput

fun main() {
    val inputView = InputView(ReadInInput(), PrintLnOutput())
    val outputView = OutputView(PrintLnOutput())
    val dealer = Dealer()
    val players = Players.of(inputView.players)

    players.players.forEach {
        it.addAll(dealer.drawFirst())
    }

    outputView.start(players)
    outputView.showAllPlayersCard(players)

    players.players.forEach {
        while (inputView.hasNextCard(it.playerName)) {
            it.add(dealer.draw())
            outputView.showPlayerCard(it)
        }
    }

    outputView.end(players)
}
