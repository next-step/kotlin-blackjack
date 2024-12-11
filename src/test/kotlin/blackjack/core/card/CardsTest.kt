package blackjack.core.card

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CardsTest {
    @Test
    fun `버스트를 테스트한다`() {
        val cards = Cards( mutableSetOf(
            Card(Denomination.ACE, Suit.HEARTS),
            Card(Denomination.KING, Suit.HEARTS),
        ))

        cards.checkBust() shouldBe false
        cards += Card(Denomination.SEVEN, Suit.HEARTS)
        cards.checkBust() shouldBe true
    }

    @Test
    fun `블랙잭을 테스트한다`() {
        val cards = Cards( mutableSetOf(
            Card(Denomination.ACE, Suit.HEARTS),
        ))

        cards.checkBlackjack() shouldBe false
        cards += Card(Denomination.KING, Suit.HEARTS)
        cards.checkBust() shouldBe true
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    fun `포인트를 계산한다`(
        cards: MutableSet<Card>,
        point: Int,
    ) {
        Cards(cards).point() shouldBe point
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
