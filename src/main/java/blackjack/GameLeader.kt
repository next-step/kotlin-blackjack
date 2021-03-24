package blackjack

import blackjack.card.Card
import blackjack.view.InputView
import blackjack.view.OutputView

class GameLeader(cards: List<Card>) {

    private val _cards: MutableList<Card> = cards.toMutableList()

    init {
        _cards.shuffle()
    }

    fun giveTwoCards(): List<Card> {
        return (0..1).map { giveCard() }
    }

    fun needToMoreCard(player: Player) {
        if (!InputView.inputReceiveCardYn(player.name)) return

        player.receiveCard(giveCard())
        OutputView.showCard(player)

        if (player.isReceiveMoreCard()) needToMoreCard(player)
    }

    private fun giveCard(): Card {
        return _cards.removeAt(0)
    }
}
