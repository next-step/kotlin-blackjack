package blackjack

import blackjack.card.Deck
import blackjack.participant.Players
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val playerNames = InputView.inputPlayerNames()

    val deck = Deck()
    val gamePlayer = Players.of(playerNames)
    val game = BlackJackGame(gamePlayer, deck)

    val startGame = game.start()
    OutputView.printPlayersStartCardPack(startGame.gamePlayers)

    gamePlayer.player.forEach { player ->
        while (player.isNotBust()) {
            val isMoreCard = InputView.inputMoreCard(player.name)
            if(!isMoreCard) {
                break
            }
            player.take(deck.pick())
            OutputView.printPlayerCard(player)
        }
    }
    OutputView.printBlackJackResult(gamePlayer.player)
}
