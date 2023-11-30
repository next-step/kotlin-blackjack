package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.CardSet

interface CardDealer {
    fun selectCard(): Card

    fun selectCard(count: Int): CardSet
}
