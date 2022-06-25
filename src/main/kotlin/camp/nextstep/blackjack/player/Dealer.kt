package camp.nextstep.blackjack.player

import camp.nextstep.blackjack.card.Card
import camp.nextstep.blackjack.card.CardDeck

class Dealer(val hand: Hand = Hand()) {

    fun serve(cardDeck: CardDeck, target: Player) {
        target.receive(cardDeck.draw())
    }

    fun receive(card: Card) {
        hand.add(card)
    }
}
