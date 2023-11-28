package blackjack

import blackjack.domain.Name
import blackjack.domain.Player
import blackjack.domain.CardDeck
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val playersName = InputView.getPlayersName()
    val players = playersName.map { Player(Name(it)) }
    val cardDeck = CardDeck()
    players.forEach {
        it.receiveCard(cardDeck.draw())
        it.receiveCard(cardDeck.draw())
    }
    OutputView.printCardState(players)
    players.forEach { player ->
        getMoreCard(player, cardDeck)
    }
}

private fun getMoreCard(player: Player, cardDeck: CardDeck) {
    while (InputView.getNeedOneMoreCard(player)) {
        player.receiveCard(cardDeck.draw())
        OutputView.printCurrentCardState(player)
    }
}
