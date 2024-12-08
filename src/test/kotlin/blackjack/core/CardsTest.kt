package blackjack.core

import blackjack.core.card.Card
import blackjack.core.card.Cards
import blackjack.core.card.Denomination
import blackjack.core.card.Suit
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CardsTest {
    @ParameterizedTest
    @MethodSource("provideParameters")
    fun `포인트를 계산한다`(
        cards: MutableSet<Card>,
        point: Int,
    ) {
        Cards(cards).point() shouldBe point
    }

    @ParameterizedTest
    @MethodSource("provideParameters2")
    fun `카드네임을 얻는다`(
        cards: MutableSet<Card>,
        cardNames: String,
    ) {
        Cards(cards).getCardNames() shouldBe cardNames
    }

    companion object {
        @JvmStatic
        fun provideParameters(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(mutableSetOf(Card(Denomination.TWO, Suit.HEARTS), Card(Denomination.THREE, Suit.HEARTS)), 5),
                Arguments.of(mutableSetOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.KING, Suit.HEARTS)), 21),
                Arguments.of(
                    mutableSetOf(
                        Card(Denomination.ACE, Suit.HEARTS),
                        Card(Denomination.KING, Suit.HEARTS),
                        Card(Denomination.KING, Suit.CLUBS),
                    ),
                    21,
                ),
                Arguments.of(
                    mutableSetOf(
                        Card(Denomination.ACE, Suit.HEARTS),
                        Card(Denomination.KING, Suit.HEARTS),
                        Card(Denomination.SEVEN, Suit.CLUBS),
                    ),
                    18,
                ),
            )
        }

        @JvmStatic
        fun provideParameters2(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(mutableSetOf(Card(Denomination.TWO, Suit.HEARTS)), "2하트"),
                Arguments.of(mutableSetOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.KING, Suit.CLUBS)), "A하트,King클로버"),
                Arguments.of(
                    mutableSetOf(
                        Card(Denomination.ACE, Suit.HEARTS),
                        Card(Denomination.KING, Suit.CLUBS),
                        Card(Denomination.QUEEN, Suit.SPADES),
                    ),
                    "A하트,King클로버,Queen스페이드",
                ),
                Arguments.of(
                    mutableSetOf(
                        Card(Denomination.ACE, Suit.HEARTS),
                        Card(Denomination.KING, Suit.CLUBS),
                        Card(Denomination.QUEEN, Suit.SPADES),
                    ),
                    "A하트,King클로버,Queen스페이드",
                ),
            )
        }
    }
}
