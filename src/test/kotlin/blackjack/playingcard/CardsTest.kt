package blackjack.playingcard

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class CardsTest {
    @Test
    fun `카드뭉치는 카드리스트로 생성된다`() {
        assertDoesNotThrow { Cards.from(listOf(Card.of(Suit.HEARTS, Symbol.ACE))) }
    }

    @Test
    internal fun `카드뭉치는 객체가 달라도, 가진 카드리스트가 같으면 동일하다`() {
        // given
        val givenCards = Cards.from(
            listOf(
                Card.of(Suit.SPADES, Symbol.TWO),
                Card.of(Suit.SPADES, Symbol.THREE)
            )
        )

        // when
        val sameAsGiven = Cards.from(
            listOf(
                Card.of(Suit.SPADES, Symbol.TWO),
                Card.of(Suit.SPADES, Symbol.THREE)
            )
        )
        val differentFromGiven = Cards.from(
            listOf(
                Card.of(Suit.SPADES, Symbol.TWO),
                Card.of(Suit.SPADES, Symbol.FOUR)
            )
        )

        // then
        assertAll(
            { assertThat(givenCards).isEqualTo(sameAsGiven) },
            { assertThat(givenCards).isNotEqualTo(differentFromGiven) }
        )
    }

    @Test
    internal fun `빈 카드`() {
        // given
        val expected = Cards.from(emptyList())

        // when
        val cards = Cards.empty()

        // then
        assertThat(cards).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource
    fun `카드뭉치에서 생성 시 주어진 카드 리스트를 그대로 반환 받을 수 있다`(cardList: List<Card>, expected: List<Card>) {
        // given
        val cards = Cards.from(cardList)

        // when
        val actual = cards.toList()

        // then
        assertThat(actual).containsExactlyElementsOf(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "SPADES, ACE",
        "HEARTS, SEVEN"
    )
    fun `카드뭉치에 카드를 추가하면 추가한 순서대로 가지고 있다`(suit: Suit, symbol: Symbol) {
        // given
        val cards = Cards.empty()
        val expected = Cards.from(listOf(Card.of(suit, symbol)))

        // when
        cards.add(Card.of(suit, symbol))

        // then
        assertThat(cards).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `카드뭉치에서 생성 시 주어진 카드 리스트를 그대로 반환 받을 수 있다`(): Stream<Arguments> = Stream.of(
            Arguments.of(
                listOf(Card.of(Suit.SPADES, Symbol.EIGHT), Card.of(Suit.DIAMONDS, Symbol.SEVEN)),
                listOf(Card.of(Suit.SPADES, Symbol.EIGHT), Card.of(Suit.DIAMONDS, Symbol.SEVEN))
            ),
            Arguments.of(
                listOf(Card.of(Suit.HEARTS, Symbol.QUEEN)),
                listOf(Card.of(Suit.HEARTS, Symbol.QUEEN))
            )
        )
    }
}
