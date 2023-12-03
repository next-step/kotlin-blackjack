package blackjack

import blackjack.domain.Deck
import blackjack.domain.Participant

class BlackjackGame {
    fun start(names: List<String>, deck: Deck): List<Participant> {
        return names.map { Participant(it, deck.getCards(2)) }
    }

    fun play(participant: Participant, deck: Deck, receive: Boolean): Boolean {
        if (receive) {
            participant.addCard(deck.getCard())
        }
        return receive
    }
}
