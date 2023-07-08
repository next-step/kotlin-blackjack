package blackjack

import domain.card.Card
import domain.card.CardDeck
import domain.card.CardType
import domain.card.Denomination

val spadeAce = Card.of(Denomination.ACE, CardType.SPADE)
val spadeTwo = Card.of(Denomination.TWO, CardType.SPADE)
val spadeThree = Card.of(Denomination.THREE, CardType.SPADE)
val spadeFour = Card.of(Denomination.FOUR, CardType.SPADE)
val spadeFive = Card.of(Denomination.FIVE, CardType.SPADE)
val spadeSix = Card.of(Denomination.SIX, CardType.SPADE)
val spadeSeven = Card.of(Denomination.SEVEN, CardType.SPADE)
val spadeEight = Card.of(Denomination.EIGHT, CardType.SPADE)
val spadeNine = Card.of(Denomination.NINE, CardType.SPADE)
val spadeTen = Card.of(Denomination.TEN, CardType.SPADE)
val spadeJack = Card.of(Denomination.JACK, CardType.SPADE)
val spadeQueen = Card.of(Denomination.QUEEN, CardType.SPADE)
val spadeKing = Card.of(Denomination.KING, CardType.SPADE)

val diamondAce = Card.of(Denomination.ACE, CardType.DIAMOND)
val diamondKing = Card.of(Denomination.KING, CardType.DIAMOND)
val diamondQueen = Card.of(Denomination.KING, CardType.DIAMOND)
val diamondJack = Card.of(Denomination.KING, CardType.DIAMOND)

val heartKing = Card.of(Denomination.KING, CardType.HEART)
val heartQueen = Card.of(Denomination.KING, CardType.HEART)
val heartJack = Card.of(Denomination.KING, CardType.HEART)
val cloverKing = Card.of(Denomination.KING, CardType.CLOVER)
val cloverQueen = Card.of(Denomination.KING, CardType.CLOVER)
val cloverJack = Card.of(Denomination.KING, CardType.CLOVER)

val cardDeckOnlyHaveKingQueenJack: CardDeck
    get() = object : CardDeck {

        var popCount = 0

        val kings: List<Card> = listOf(
            spadeKing,
            diamondKing,
            heartKing,
            cloverKing,
            spadeQueen,
            diamondQueen,
            heartQueen,
            cloverQueen,
            spadeJack,
            diamondJack,
            heartJack,
            cloverJack,
        )

        override fun pop(): Card {
            return kings[popCount++]
        }
    }
