package blackjack

import blackjack.domain.Game
import blackjack.domain.Game.Companion.DEFAULT_CARD_AMOUNT
import blackjack.domain.Game.Companion.REPLY_NO
import blackjack.domain.Game.Companion.REPLY_YES
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {

    val playerNames = InputView.readPlayerNames()
    val game = Game(playerNames)
    ResultView.showResultOfSetUp(game.setUp())

    val players = game.players
    players.forEach { player ->
        var reply = InputView.readReplyToDrawing(player)
        while (reply != REPLY_NO && reply.startsWith(REPLY_YES)) {
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
