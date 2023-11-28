package blackjack

import blackjack.domain.Dealer
import blackjack.domain.ShuffledCardDeck
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val cardDeck = ShuffledCardDeck()

    val dealer = Dealer(cardDeck)
    val players = createPlayers(cardDeck)
    val participants = listOf(dealer) + players

    OutputView.printParticipantOpenedCards(participants)
    obtainCards(players, dealer)
    OutputView.printParticipantHands(participants)

    val compareResults = dealer.compareWith(*players.toTypedArray())
    OutputView.printCompareResults(compareResults)
}

private fun createPlayers(cardDeck: ShuffledCardDeck): List<Player> {
    val names = InputView.inputNames()
    return names.map { Player(it, cardDeck) }
}

private fun obtainCards(players: List<Player>, dealer: Dealer) {
    players.forEach { obtainPlayerCard(it) }
    obtainDealerCard(dealer)
}

private fun obtainPlayerCard(player: Player) {
    while (isPlayerObtainCard(player)) {
        player.obtain()
        OutputView.printParticipantCards(player.name, player.hands)
    }
}

private fun isPlayerObtainCard(player: Player): Boolean {
    return player.isObtainable() && InputView.inputIsObtainCard(player.name)
}

private fun obtainDealerCard(dealer: Dealer) {
    while(dealer.isObtainable()) {
        dealer.obtain()
        OutputView.printObtainDealerCard()
    }
}
