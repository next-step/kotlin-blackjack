package blackjack.player

import blackjack.playingcard.Card
import blackjack.playingcard.Cards
import blackjack.playingcard.Suit
import blackjack.playingcard.Symbol
import blackjack.playingcard.Value
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.aggregator.AggregateWith
import org.junit.jupiter.params.aggregator.ArgumentsAccessor
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException
import org.junit.jupiter.params.aggregator.ArgumentsAggregator
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.platform.commons.util.Preconditions
import java.util.stream.Stream

internal class HandTest {
    @Test
    fun `손패는 카드리스트를 가진다`() {
        assertDoesNotThrow { Hand(cards = Cards.empty()) }
    }

    @ParameterizedTest
    @CsvSource(
        "SPADES, ACE",
        "HEARTS, SEVEN"
    )
    fun `손패에 카드를 주어 카드를 추가할 수 있다`(suit: Suit, symbol: Symbol) {
        // given
        val givenCard = Card.of(Suit.DIAMONDS, Symbol.NINE)
        val hand = Hand(Cards.from(listOf(givenCard)))

        val newCard = Card.of(suit, symbol)
        val expected = Cards.from(listOf(givenCard, newCard))

        // when
        hand.add(newCard)
        val actual = hand.cards

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `손패는 아무 값 없이 생성하면 비어있다`() {
        val actual = Hand().cards

        assertThat(actual).isEqualTo(Cards.empty())
    }

    @ParameterizedTest
    @MethodSource
    fun `손패에 A가 없을 때, 손패 값은 카드 값의 합과 같다`(expected: Value, cards: Cards) {
        // when
        val actual = Hand(cards).calculateValues()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `A를 11로 했을 때 손패의 합이 21을 넘지 않으면, 손패의 A들은 그대로 11로 취급한다`() {
        // given
        val cards = Cards.from(
            listOf(
                Card.of(Suit.HEARTS, Symbol.ACE),
                Card.of(Suit.HEARTS, Symbol.TWO)
            )
        )
        val hand = Hand(cards)
        val expectedValue = Value(13)

        // when
        val actualValue: Value = hand.calculateValues()

        // then
        assertThat(actualValue).isEqualTo(expectedValue)
    }

    @ParameterizedTest
    @CsvSource(
        "12, ACE, ACE",
        "13, ACE, TEN, TWO",
        "21, TEN, TEN, ACE"
    )
    fun `A를 11로 했을 때 손패의 합이 21을 넘으면, 손패의 A들을 1 또는 11로 간주하여 21 이하인 가장 큰 합으로 반환한다`(
        expected: Int,
        @AggregateWith(SymbolVarargsAggregator::class) vararg symbols: Symbol
    ) {
        // given
        val cards = Cards.from(symbols.map { symbol -> Card.of(Suit.SPADES, symbol) })
        val hand = Hand(cards)
        val expectedValue = Value(expected)

        // when
        val actualValue: Value = hand.calculateValues()

        // then
        assertThat(actualValue).isEqualTo(expectedValue)
    }

    @Test
    internal fun `손패의 합이 21을 초과하면 버스트 상태이다`() {
        val cardsOver21 = Cards.from(
            listOf(
                Card.of(Suit.SPADES, Symbol.TEN),
                Card.of(Suit.HEARTS, Symbol.TEN),
                Card.of(Suit.DIAMONDS, Symbol.TWO)
            )
        )
        // given
        val hand = Hand(cardsOver21)

        // then
        assertThat(hand.status).isEqualTo(Hand.Status.BUST)
    }

    @Test
    internal fun `손패의 합이 21 이하이면 버스트가 아닌 상태이다`() {
        val cardsLessOrEqual21 = Cards.from(
            listOf(
                Card.of(Suit.SPADES, Symbol.TEN),
                Card.of(Suit.DIAMONDS, Symbol.ACE)
            )
        )
        // given
        val hand = Hand(cardsLessOrEqual21)

        // then
        assertThat(hand.status).isEqualTo(Hand.Status.NOT_BUST)
    }

    @ParameterizedTest
    @CsvSource(
        "TWO, NOT_BUST",
        "THREE, BUST"
    )
    internal fun `손패가 버스트 상태가 아닐 때 카드를 추가하면, 가지고 있는 카드에 주어진 카드를 추가하고 나서 그 상태를 반환한다`(
        addingCardSymbol: Symbol,
        expectedStatus: Hand.Status
    ) {
        // given
        val cardsWithValue19 = Cards.from(
            listOf(
                Card.of(Suit.SPADES, Symbol.TEN),
                Card.of(Suit.SPADES, Symbol.NINE)
            )
        )
        val hand = Hand(cardsWithValue19)
        val addingCard = Card.of(Suit.HEARTS, addingCardSymbol)

        val expectedHandCards = Cards.from(
            listOf(
                Card.of(Suit.SPADES, Symbol.TEN),
                Card.of(Suit.SPADES, Symbol.NINE),
                addingCard
            )
        )

        // when
        val actualStatus = hand.add(addingCard)
        val actualHandCards = hand.cards

        // then
        assertAll(
            { assertThat(actualStatus).isEqualTo(expectedStatus).isEqualTo(hand.status) },
            { assertThat(actualHandCards).isEqualTo(expectedHandCards) }
        )
    }

    @Test
    internal fun `손패가 버스트 상태일 때 카드를 추가하면, 손패에 카드를 추가하지 않고 버스트 상태를 반환한다`() {
        // given
        val cardsWithValue22 = Cards.from(
            listOf(
                Card.of(Suit.SPADES, Symbol.TEN),
                Card.of(Suit.HEARTS, Symbol.TEN),
                Card.of(Suit.DIAMONDS, Symbol.TWO)
            )
        )
        val hand = Hand(cardsWithValue22)
        val addingCard = Card.of(Suit.CLUBS, Symbol.ACE)

        val expectedCards = Cards.from(
            listOf(
                Card.of(Suit.SPADES, Symbol.TEN),
                Card.of(Suit.HEARTS, Symbol.TEN),
                Card.of(Suit.DIAMONDS, Symbol.TWO)
            )
        )

        // when
        val actualStatus: Hand.Status = hand.add(addingCard)
        val actualHandCards: Cards = hand.cards

        // then
        assertAll(
            { assertThat(actualStatus).isEqualTo(Hand.Status.BUST) },
            { assertThat(actualHandCards).isEqualTo(expectedCards) }
        )
    }

    // 참고: https://github.com/junit-team/junit5/issues/2256#issuecomment-612438057
    internal class SymbolVarargsAggregator : ArgumentsAggregator {
        @Throws(ArgumentsAggregationException::class)
        override fun aggregateArguments(accessor: ArgumentsAccessor, context: ParameterContext): Array<Symbol> {
            val parameterType = context.parameter.type
            Preconditions.condition(parameterType.isArray) { "must be an array type, but was $parameterType" }
            val componentType = parameterType.componentType

            return (context.index until accessor.size())
                .map { index -> accessor[index, componentType] as Symbol }
                .toTypedArray()
        }
    }

    companion object {
        @JvmStatic
        fun `손패에 A가 없을 때, 손패 값은 카드 값의 합과 같다`(): Stream<Arguments> = Stream.of(
            Arguments.of(Value.ZERO, Cards.empty()),
            Arguments.of(Value(11), Cards.from(listOf(Card.of(Suit.SPADES, Symbol.ACE)))),
            Arguments.of(
                Value(15),
                Cards.from(
                    listOf(
                        Card.of(Suit.SPADES, Symbol.FIVE),
                        Card.of(Suit.SPADES, Symbol.TEN)
                    )
                )
            ),
            Arguments.of(
                Value(30),
                Cards.from(
                    listOf(
                        Card.of(Suit.SPADES, Symbol.KING),
                        Card.of(Suit.SPADES, Symbol.QUEEN),
                        Card.of(Suit.SPADES, Symbol.JACK)
                    )
                )
            )
        )
    }
}
