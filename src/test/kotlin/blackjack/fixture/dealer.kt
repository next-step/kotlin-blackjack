package blackjack.fixture

import blackjack.domain.card.Card

import blackjack.domain.player.Dealer
import blackjack.domain.player.PlayerName


object DealerFixture {
    fun of(vararg cards: Card): Dealer {
        require(cards.size >= 2)

        val customCardDeck = CustomCardDeck.of(cards[0], cards[1])
        val dealer: Dealer = Dealer.of(PlayerName.from("dealer"), customCardDeck)
        dealer.openSelf()

        if (cards.size > 2) {
            val afterOpen = cards.toList().subList(2, cards.size)
            afterOpen.forEach { dealer.hit(it) }
        }

        return dealer
    }
}
