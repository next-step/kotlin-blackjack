package blackjack.domain.player

import blackjack.domain.card.Card

class Dealer(startingCards: List<Card>) : Player("딜러", startingCards) {
    val shouldDrawCard: Boolean
        get() = cards.total.value <= 16
}
