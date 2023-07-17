package blackjack

import blackjack.domain.Card
import blackjack.domain.Hands
import blackjack.domain.Symbol
import blackjack.domain.Type

fun cardOf(type: Type, symbol: Symbol): Card {
    val card = Card.CARD_DECK["${type.name}${symbol.name}"]
    require(card != null) { "type:${type.name} 과 symbol:${symbol.name} 에 일치하는 카드가 없습니다." }

    return card
}

val CLUBS_TWO = cardOf(Type.CLUBS, Symbol.TWO)
val SPADES_ACE = cardOf(Type.SPADES, Symbol.ACE)
val SPADES_FOUR = cardOf(Type.SPADES, Symbol.FOUR)
val SPADES_SIX = cardOf(Type.SPADES, Symbol.SIX)
val HEARTS_KING = cardOf(Type.HEARTS, Symbol.KING)
val SPADES_KING = cardOf(Type.SPADES, Symbol.KING)

val BLACKJACK_HANDS = Hands(listOf(SPADES_ACE, HEARTS_KING))
