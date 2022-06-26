package camp.nextstep.blackjack.player

import camp.nextstep.blackjack.card.Card
import camp.nextstep.blackjack.card.CardDeck
import camp.nextstep.blackjack.card.CardNumber
import camp.nextstep.blackjack.card.DrawnCard
import camp.nextstep.blackjack.card.Hand

class Dealer : Player {

    override val name = "Dealer"

    override val hand = Hand()

    override fun receive(card: Card) {
        if (hand.cards.isEmpty()) {
            hand.add(DrawnCard(card).apply { turnUp() })
        } else if (hand.cards.size > 2 ||
            hand.cards[0].card.number == CardNumber.ACE ||
            hand.cards[0].card.number == CardNumber.TEN
        ) {
            hand.add(DrawnCard(card).apply { turnUp() })
        } else {
            hand.add(DrawnCard(card))
        }
    }

    fun serve(cardDeck: CardDeck, target: Player) {
        target.receive(cardDeck.draw())
    }

    fun serve(cardDeck: CardDeck, target: List<Player>) {
        for (player in target) {
            player.receive(cardDeck.draw())
        }
    }
}
