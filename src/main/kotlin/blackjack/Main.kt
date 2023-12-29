package blackjack

import blackjack.domain.BlackJack
import blackjack.domain.ShuffledCardDeck
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val cardDeck = ShuffledCardDeck()
    val playerNames = InputView.inputNames()
    val blackjack = BlackJack(cardDeck, *playerNames.toTypedArray())

    OutputView.printParticipantOpenedCards(blackjack.openCardsOfParticipant())
    obtainCards(playerNames, blackjack)
    OutputView.printCompareResults(blackjack.compareResults())
}

private fun obtainCards(playerNames: List<String>, blackjack: BlackJack) {
    obtainCardsForPlayers(playerNames, blackjack)
    obtainCardsForDealer(blackjack)
    OutputView.printParticipantHands(blackjack.participants())
}

private fun obtainCardsForDealer(blackjack: BlackJack) {
    while (blackjack.isDealerObtainable()) {
        blackjack.obtainDealerCard()
        OutputView.printObtainDealerCard()
    }
}

private fun obtainCardsForPlayers(playerNames: List<String>, blackjack: BlackJack) {
    playerNames.forEach { obtainCardsForPlayer(it, blackjack) }
}

private fun obtainCardsForPlayer(name: String, blackjack: BlackJack) {
    val wantToTake = { InputView.inputIsObtainCard(name) }
    while (blackjack.isPlayerObtainable(name, wantToTake)) {
        val cards = blackjack.obtainPlayerCard(name)
        OutputView.printParticipantCards(name, cards)
    }
}
