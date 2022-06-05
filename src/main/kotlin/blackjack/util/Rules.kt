package blackjack.util

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import blackjack.domain.card.CardSymbol

const val ACE_MIN_NUMBER: Int = 1
const val ACE_MAX_NUMBER: Int = 11
const val FIRST_DRAW_NUMBER = 2
val ALL_CARDS = CardSuit.values().flatMap { suit ->
    CardSymbol.values().map { symbol ->
        Card(suit, symbol)
    }
}
