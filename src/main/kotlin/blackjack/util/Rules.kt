package blackjack.util

import blackjack.card.Card
import blackjack.card.CardSuit
import blackjack.card.CardSymbol

const val COUNT_THRESHOLD: Int = 21
const val ACE_MIN_NUMBER: Int = 1
const val ACE_MAX_NUMBER: Int = 11
val ALL_CARDS = CardSuit.values().flatMap { suit ->
    CardSymbol.values().map { symbol ->
        Card(suit, symbol)
    }
}
