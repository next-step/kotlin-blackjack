package blackjack

import blackjack.card.Deck
import blackjack.dealer.Dealer
import blackjack.factory.PlayerCreator
import blackjack.factory.SimpleCardCreator
import blackjack.ui.input.BlackjackInputView
import blackjack.ui.output.ResultView

fun main() {
    val playerNames = BlackjackInputView.createPlayer()
    val players = PlayerCreator.create(playerNames)
    val deck = Deck(SimpleCardCreator.startCard())
    val dealer = Dealer(deck)

    val blackJack = BlackJackGame(players, dealer)

    blackJack.startGame()
    ResultView.showStartStatus(
        blackJack.allPlayer()
    )
    println()

    play(blackJack)

    println()

    ResultView.showGameResult(blackJack.allPlayer())
}

private fun play(blackJack: BlackJackGame) {
    val player = blackJack.getPlayablePlayer()
    if (player != null) {
        val needMoreCard = BlackjackInputView.needMoreCard(player.name)
        blackJack.ask(player, needMoreCard)
        ResultView.showPlayerCard(player)
        play(blackJack)
    }
}
