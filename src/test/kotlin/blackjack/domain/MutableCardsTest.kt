package blackjack.domain

import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MutableCardsTest {
    @Test
    fun `카드를 추가할 수 있다`() {
        val mutableCards = MutableCards(mutableListOf())
        val card = Card(Suit.SPADES, Rank.NINE)
        mutableCards.add(card)
        mutableCards.cards.contains(card) shouldBeEqual true
    }

    @Test
    fun `가지고 있는 카드 alias를 모두 나열 할 수 있다`() {
        val mutableCards = MutableCards(mutableListOf())
        mutableCards.add(Card(Suit.DIAMONDS, Rank.NINE))
        mutableCards.add(Card(Suit.HEARTS, Rank.ACE))
        mutableCards.cardsToString() shouldBeEqual "9다이아몬드, A하트"
    }

    @MethodSource("카드와 카드 합산 제공")
    @ParameterizedTest
    fun `가지고 있는 카드를 합산할 수 있다`(
        card1: Card,
        card2: Card,
        card3: Card,
        sum: Int,
    ) {
        val mutableCards = MutableCards(mutableListOf())
        mutableCards.add(card1)
        mutableCards.add(card2)
        mutableCards.add(card3)
        mutableCards.sumValues() shouldBeEqual sum
    }

    fun `카드와 카드 합산 제공`(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(Card(Suit.CLUBS, Rank.ACE), Card(Suit.SPADES, Rank.ACE), Card(Suit.DIAMONDS, Rank.ACE), 13),
            Arguments.of(Card(Suit.HEARTS, Rank.JACK), Card(Suit.SPADES, Rank.QUEEN), Card(Suit.DIAMONDS, Rank.ACE), 21),
            Arguments.of(Card(Suit.SPADES, Rank.TWO), Card(Suit.DIAMONDS, Rank.TWO), Card(Suit.DIAMONDS, Rank.TWO), 6),
        )
    }
}
