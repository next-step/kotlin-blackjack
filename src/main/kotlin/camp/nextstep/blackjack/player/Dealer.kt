package camp.nextstep.blackjack.player

import camp.nextstep.blackjack.card.CardDeck

class Dealer : Player {

    override val name = "Dealer"

    override val hand = Hand()

    fun serve(cardDeck: CardDeck, target: Player) {
        target.receive(cardDeck.draw())
    }

    fun serve(cardDeck: CardDeck, target: List<Player>) {
        for (player in target) {
            player.receive(cardDeck.draw())
        }
    }
}
