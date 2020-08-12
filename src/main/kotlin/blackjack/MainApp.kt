package blackjack

import blackjack.domain.Game
import blackjack.domain.Game.Companion.DEFAULT_CARD_AMOUNT
import blackjack.view.InputView
import blackjack.view.REPLY_YES
import blackjack.view.ResultView

fun main() {

    val playerNames = InputView.readPlayerNames()
    val game = Game(playerNames)
    ResultView.showResultOfSetUp(game.setUp())

    val players = game.players
    players.forEach { player ->
        var reply = InputView.readReplyToDrawing(player)
        while (REPLY_YES == reply) {
            game.giveChanceToDraw(player)
            ResultView.showStateOfCards(player)
            reply = InputView.readReplyToDrawing(player)
        }
        val currentCardAmount = player.amountOfCards()
        if (currentCardAmount == DEFAULT_CARD_AMOUNT) {
            ResultView.showStateOfCards(player)
        }
    }

    ResultView.showFinalResult(players)
}
