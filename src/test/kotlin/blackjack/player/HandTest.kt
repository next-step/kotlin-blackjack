package blackjack.player

import blackjack.playingcard.Card
import blackjack.playingcard.Cards
import blackjack.playingcard.Suit
import blackjack.playingcard.Symbol
import blackjack.playingcard.Value
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
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
        assertDoesNotThrow { Hand(cards = Cards()) }
    }

    @ParameterizedTest
    @CsvSource(
        "SPADES, ACE",
        "HEARTS, SEVEN"
    )
    fun `손패에 카드를 주어 카드를 추가할 수 있다`(suit: Suit, symbol: Symbol) {
        // given
        val givenCard = Card.of(Suit.DIAMONDS, Symbol.NINE)
        val hand = Hand(Cards(listOf(givenCard)))

        val newCard = Card.of(suit, symbol)
        val expected = listOf(givenCard, newCard)

        // when
        hand.add(newCard)
        val actual = hand.cards.toList()

        // then
        assertThat(actual).containsExactlyElementsOf(expected)
    }

    @Test
    internal fun `손패는 아무 값 없이 생성하면 비어있다`() {
        val actual = Hand().cards.toList()

        assertThat(actual).isEmpty()
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
        val cards = Cards(
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
        val cards = Cards(symbols.map { symbol -> Card.of(Suit.SPADES, symbol) })
        val hand = Hand(cards)
        val expectedValue = Value(expected)

        // when
        val actualValue: Value = hand.calculateValues()

        // then
        assertThat(actualValue).isEqualTo(expectedValue)
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
            Arguments.of(Value.ZERO, Cards(emptyList())),
            Arguments.of(Value(11), Cards(listOf(Card.of(Suit.SPADES, Symbol.ACE)))),
            Arguments.of(
                Value(15),
                Cards(
                    listOf(
                        Card.of(Suit.SPADES, Symbol.FIVE),
                        Card.of(Suit.SPADES, Symbol.TEN)
                    )
                )
            ),
            Arguments.of(
                Value(30),
                Cards(
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
