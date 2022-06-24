package blackjack

import blackjack.card.Deck
import blackjack.dealer.Dealer
import blackjack.player.PlayerCreator
import blackjack.ui.input.BlackjackInputView
import blackjack.ui.output.ResultView

fun main() {
    val playerNames = BlackjackInputView.createPlayerNames()
    val players = PlayerCreator.create(playerNames)
    val deck = Deck.init()
    val dealer = Dealer(deck)

    val blackJack = BlackjackGame(players, dealer)

    blackJack.startGame()
    ResultView.showStartStatus(
        blackJack.players
    )
    println()

    play(blackJack)

    println()

    ResultView.showGameResult(blackJack.players)
}

private fun play(blackjack: BlackjackGame) {
    val player = blackjack.getPlayablePlayer()
    if (player != null) {
        val needMoreCard = BlackjackInputView.needMoreCard(player.name)
        blackjack.ask(player, needMoreCard)
        ResultView.showPlayerCard(player)
        play(blackjack)
    }
}
