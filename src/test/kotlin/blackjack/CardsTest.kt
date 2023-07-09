package blackjack

import domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CardsTest {
    @ParameterizedTest
    @MethodSource("카드 더 받을 수 있는지 확인 테스트 데이터")
    fun `갖고있는 카드에서 결과 계산 테스트`(cards: Cards, result: Int) {
        assertThat(cards.score()).isEqualTo(result)
    }

    companion object {
        @JvmStatic
        fun `카드 더 받을 수 있는지 확인 테스트 데이터`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Cards(listOf(spadeTwo, spadeThree)), 5
                ),

                Arguments.of(
                    Cards(listOf(spadeAce, diamondAce)), 12
                ),

                Arguments.of(
                    Cards(listOf(spadeAce, spadeKing)), 21
                ),

                Arguments.of(
                    Cards(
                        listOf(
                            spadeAce,
                            spadeNine,
                            diamondAce
                        )
                    ),
                    21
                ),

                Arguments.of(
                    Cards(
                        listOf(
                            spadeTwo,
                            spadeTen,
                            spadeKing
                        )
                    ),
                    22
                ),
            )
        }
    }
}
