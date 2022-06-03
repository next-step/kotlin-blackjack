package blackjack

import blackjack.domain.CardGame
import blackjack.ui.InputReceiver
import blackjack.ui.UI

fun main() {

    val players = InputReceiver.receiverPlayers()

    val cardGame = CardGame(players)

    cardGame.distribute(2)
    UI.drawCardDistributeMessage(players, 2)


}
