package blackjack

import domain.card.Card
import domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CardsTest {
    @ParameterizedTest
    @MethodSource("카드 더 받을 수 있는지 확인 테스트 데이터")
    fun `갖고있는 카드에서 결과 계산 테스트`(cards: List<Card>, result: Int) {
        assertThat(Cards(cards).result()).isEqualTo(result)
    }

    companion object {
        @JvmStatic
        fun `카드 더 받을 수 있는지 확인 테스트 데이터`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(spadeTwo, spadeThree), 5
                ),

                Arguments.of(
                    listOf(spadeAce, diamondAce), 2
                ),

                Arguments.of(
                    listOf(spadeAce, spadeKing), 21
                ),

                Arguments.of(
                    listOf(
                        spadeAce,
                        spadeNine,
                        diamondAce
                    ),
                    21
                ),

                Arguments.of(
                    listOf(
                        spadeTwo,
                        spadeTen,
                        spadeKing
                    ),
                    22
                ),
            )
        }
    }
}
