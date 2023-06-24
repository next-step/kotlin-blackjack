package blackjack.card

import domain.card.BlackjackCard
import domain.card.BlackjackCards
import domain.card.CardNumber
import domain.card.Suit
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class BlackjackCardsTest {

    @ParameterizedTest
    @MethodSource("getCardNumberSumLessThanEquals21")
    fun `카드들의 숫자 합이 21 미만이라면 카드를 뽑을 수 있다`(cards: BlackjackCards) {
        cards.isDrawable() shouldBe true
    }

    @ParameterizedTest
    @MethodSource("getCardNumberSum")
    fun `카드들의 숫자 합은 21 또는 21에 가장 가까운 숫자를 가진다`(cards: BlackjackCards, expected: Int) {
        cards.sum() shouldBe expected
    }

    companion object {
        @JvmStatic
        fun getCardNumberSumLessThanEquals21(): List<Arguments> = listOf(
            Arguments.of(
                BlackjackCards(
                    listOf(
                        BlackjackCard(suit = Suit.SPADE, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.HEART, number = CardNumber.TWO),
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.THREE),
                    ),
                ),
            ),
            Arguments.of(
                BlackjackCards(
                    listOf(
                        BlackjackCard(suit = Suit.SPADE, number = CardNumber.KING),
                        BlackjackCard(suit = Suit.HEART, number = CardNumber.NINE),
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                    ),
                ),
            ),
        )

        @JvmStatic
        fun getCardNumberSum(): List<Arguments> = listOf(
            Arguments.of(
                BlackjackCards(
                    listOf(
                        BlackjackCard(suit = Suit.SPADE, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.HEART, number = CardNumber.TWO),
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.THREE),
                    ),
                ),
                16,
            ),
            Arguments.of(
                BlackjackCards(
                    listOf(
                        BlackjackCard(suit = Suit.SPADE, number = CardNumber.KING),
                        BlackjackCard(suit = Suit.HEART, number = CardNumber.NINE),
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                    ),
                ),
                20,
            ),
            Arguments.of(
                BlackjackCards(
                    listOf(
                        BlackjackCard(suit = Suit.SPADE, number = CardNumber.KING),
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                    ),
                ),
                21,
            ),
            Arguments.of(
                BlackjackCards(
                    listOf(
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.HEART, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.SPADE, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.DIAMOND, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.HEART, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.SPADE, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.FIVE),
                    ),
                ),
                12,
            ),
            Arguments.of(
                BlackjackCards(
                    listOf(
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.HEART, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.SPADE, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.DIAMOND, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.HEART, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.SPADE, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.FIVE),
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.FIVE),
                    ),
                ),
                16,
            ),
            Arguments.of(
                BlackjackCards(
                    listOf(
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.HEART, number = CardNumber.TWO),
                        BlackjackCard(suit = Suit.SPADE, number = CardNumber.THREE),
                        BlackjackCard(suit = Suit.DIAMOND, number = CardNumber.FOUR),
                        BlackjackCard(suit = Suit.HEART, number = CardNumber.FIVE),
                        BlackjackCard(suit = Suit.SPADE, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.SIX),
                    ),
                ),
                22,
            ),
        )
    }
}
