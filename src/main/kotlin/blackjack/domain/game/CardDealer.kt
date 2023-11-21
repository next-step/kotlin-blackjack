package blackjack.domain.game

import blackjack.domain.card.Card

interface CardDealer {
    fun selectCard(): Card
}
