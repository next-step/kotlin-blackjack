package fixtures

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Suit

private val SUIT = Suit.SPADES
private val DENOMINATION = Denomination.TEN
private val BLACKJACK_CARDS = listOf(
    Card(Suit.SPADES, Denomination.ACE),
    Card(Suit.SPADES, Denomination.KING),
)
private val BUST_CARDS = listOf(
    Card(Suit.SPADES, Denomination.KING),
    Card(Suit.SPADES, Denomination.KING),
    Card(Suit.SPADES, Denomination.KING),
)
private val UNDER_BLACKJACK_CARDS = listOf(
    Card(Suit.SPADES, Denomination.KING),
    Card(Suit.SPADES, Denomination.TWO),
)
private val OVER_DEALER_STAND_CARDS = listOf(
    Card(Suit.SPADES, Denomination.QUEEN),
    Card(Suit.SPADES, Denomination.EIGHT),
)
private val UNDER_DEALER_STAND_CARDS = listOf(
    Card(Suit.SPADES, Denomination.QUEEN),
    Card(Suit.SPADES, Denomination.TWO),
)

fun createCard(
    suit: Suit = SUIT,
    denomination: Denomination = DENOMINATION
): Card {
    return Card(suit, denomination)
}

fun createCards(vararg cards: Card): Cards = Cards(cards.toList())

/*
* 테스트 데이터 생성시
* 불필요한 데이터 나열을 막기 위해
* 주어진 메서드를 최대한 활용하도록 한다.
* */
fun createBustCards(): Cards = Cards(BUST_CARDS)
fun createBlackjackCards(): Cards = Cards(BLACKJACK_CARDS)
fun createUnderBlackjackCards(): Cards = Cards(UNDER_BLACKJACK_CARDS)
fun createOverDealerStandCards(): Cards = Cards(OVER_DEALER_STAND_CARDS)
fun createUnderDealerStandCards(): Cards = Cards(UNDER_DEALER_STAND_CARDS)
