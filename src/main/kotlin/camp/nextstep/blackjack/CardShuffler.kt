package camp.nextstep.blackjack

import camp.nextstep.blackjack.card.CardDeck

object CardShuffler {

    fun shuffle(cardDeck: CardDeck): CardDeck {
        val shuffled = cardDeck.cards.toMutableList().shuffled()
        return CardDeck.of(shuffled)
    }
}
