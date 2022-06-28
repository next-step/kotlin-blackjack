package camp.nextstep.blackjack.player

import camp.nextstep.blackjack.card.Card
import camp.nextstep.blackjack.card.CardDeck
import camp.nextstep.blackjack.card.DrawnCard
import camp.nextstep.blackjack.card.Hand

class Dealer : Player {

    override val name = "Dealer"

    override val hand = Hand()

    override fun receive(card: Card) {
        val dealerCardShouldTurnUp = hand.isEmpty() || hand.cards.size >= 2 || hand.firstCardIsAce() || hand.firstCardIsTen()

        if (dealerCardShouldTurnUp) {
            hand.add(DrawnCard(card).apply { turnUp() })
        } else {
            hand.add(DrawnCard(card))
        }
    }

    fun openCards() {
        hand.turnUp()
    }

    fun serve(cardDeck: CardDeck, player: Player) {
        player.receive(cardDeck.draw())
    }

    fun serve(cardDeck: CardDeck, players: List<Player>) {
        for (player in players) {
            player.receive(cardDeck.draw())
        }
    }
}
