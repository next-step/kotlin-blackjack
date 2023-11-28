package blackjack

import blackjack.domain.Name
import blackjack.domain.Player
import blackjack.domain.PlayingCard
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val playersName = InputView.getPlayersName()
    val players = playersName.map { Player(Name(it)) }
    val playingCard = PlayingCard()
    players.forEach {
        it.receiveCard(playingCard.draw())
        it.receiveCard(playingCard.draw())
    }
    OutputView.printCardState(players)
}
