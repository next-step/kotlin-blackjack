package blackjack

import domain.card.Card
import domain.card.Clover
import domain.card.Denomination
import domain.card.Diamond
import domain.card.Heart
import domain.card.Spade
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CardTest {
    @ParameterizedTest
    @MethodSource("카드 숫자 테스트 데이터")
    fun `카드 숫자 테스트`(card: Card, numbers: Set<Int>) {
        assertThat(card.numbers).isEqualTo(numbers)
    }

    @Test
    fun `스페이드 덱 생성 테스트`() {
        assertThat(
            Spade.createDeck()
        ).containsAll(
            Denomination.values()
                .map { Spade.get(it) }
        )
    }

    @Test
    fun `하트 덱 생성 테스트`() {
        assertThat(
            Heart.createDeck()
        ).containsAll(
            Denomination.values()
                .map { Heart.get(it) }
        )
    }

    @Test
    fun `다이아몬드 덱 생성 테스트`() {
        assertThat(
            Diamond.createDeck()
        ).containsAll(
            Denomination.values()
                .map { Diamond.get(it) }
        )
    }

    @Test
    fun `클로버 덱 생성 테스트`() {
        assertThat(
            Clover.createDeck()
        ).containsAll(
            Denomination.values()
                .map { Clover.get(it) }
        )
    }

    companion object {
        @JvmStatic
        fun `카드 숫자 테스트 데이터`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Spade.get(Denomination.ACE), Denomination.ACE.numbers
                ),
                Arguments.of(
                    Spade.get(Denomination.TWO), Denomination.TWO.numbers
                ),
                Arguments.of(
                    Spade.get(Denomination.THREE), Denomination.THREE.numbers
                ),
                Arguments.of(
                    Spade.get(Denomination.FOUR), Denomination.FOUR.numbers
                ),
                Arguments.of(
                    Spade.get(Denomination.FIVE), Denomination.FIVE.numbers
                ),
                Arguments.of(
                    Spade.get(Denomination.SIX), Denomination.SIX.numbers
                ),
                Arguments.of(
                    Spade.get(Denomination.SEVEN), Denomination.SEVEN.numbers
                ),
                Arguments.of(
                    Spade.get(Denomination.EIGHT), Denomination.EIGHT.numbers
                ),
                Arguments.of(
                    Spade.get(Denomination.NINE), Denomination.NINE.numbers
                ),
                Arguments.of(
                    Spade.get(Denomination.TEN), Denomination.TEN.numbers
                ),
                Arguments.of(
                    Spade.get(Denomination.KING), Denomination.KING.numbers
                ),
                Arguments.of(
                    Spade.get(Denomination.QUEEN), Denomination.QUEEN.numbers
                ),
                Arguments.of(
                    Spade.get(Denomination.JACK), Denomination.JACK.numbers
                ),
            )
        }
    }
}
