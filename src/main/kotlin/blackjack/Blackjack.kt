package blackjack

import blackjack.domain.Participants
import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.view.GameView
import blackjack.view.InputView

fun main() {
    val deck = Deck(Card.createDeck())
    val playerNames = InputView.createParticipants()
    val participants = Participants.of(playerNames, deck)
    GameView.giveCard(participants)
    participants.giveCardFirstTime()
    GameView.displayInitialCard(participants)
    participants.players.forEach {
        while (InputView.needMoreCard(it)) {
            it.addCard()
            GameView.displayPlayerCard(it)
        }
    }
}
