package blackjack.domain

import blackjack.domain.Constants.INITIAL_CARD_COUNT

class Game(val players: Players, val dealer: Dealer = Dealer()) {

    init {
        initialCard()
    }

    private fun initialCard() {
        dealer.shuffle()

        players.list.forEach { player ->
            repeat(INITIAL_CARD_COUNT) {
                player.cards.add(dealer.divide())
            }
        }
    }
}
