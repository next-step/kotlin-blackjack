package blackjack.domain

import blackjack.domain.card.BlackJackCard
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardType
import blackjack.domain.player.GameUser
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameUserTest {
    @ParameterizedTest
    @ValueSource(strings = ["A", "B", "C"])
    fun `사용자를 생성한다`(name: String) {
        val user = GameUser(name)
        (user.name == name) shouldBe true
    }

    @ParameterizedTest
    @MethodSource("generateCardNumbers")
    fun `점수를 계산한다`(
        numbers: List<BlackJackCard>,
        points: Int,
    ) {
        val user = GameUser("홍길동")
        numbers.forEach {
            user.cards.add(it)
        }
        user.points shouldBe points
    }

    @ParameterizedTest
    @MethodSource("generateBlackjackCard")
    fun `블랙잭이 맞는지 확인`(numbers: List<BlackJackCard>) {
        val user = GameUser("홍길동")
        numbers.forEach {
            user.cards.add(it)
        }
        user.points shouldBe 21
    }

    private fun generateCardNumbers(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_A),
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_2),
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_3),
                ),
                16,
            ),
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_J),
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_Q),
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_K),
                ),
                30,
            ),
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.CLOVER, CardNumber.CARD_A),
                    BlackJackCard(CardType.CLOVER, CardNumber.CARD_2),
                    BlackJackCard(CardType.CLOVER, CardNumber.CARD_3),
                ),
                16,
            ),
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.DIAMOND, CardNumber.CARD_A),
                    BlackJackCard(CardType.DIAMOND, CardNumber.CARD_2),
                    BlackJackCard(CardType.DIAMOND, CardNumber.CARD_3),
                ),
                16,
            ),
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.HEART, CardNumber.CARD_A),
                    BlackJackCard(CardType.HEART, CardNumber.CARD_2),
                    BlackJackCard(CardType.HEART, CardNumber.CARD_3),
                ),
                16,
            ),
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_J),
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_Q),
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_10),
                ),
                30,
            ),
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_A),
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_K),
                ),
                21,
            ),
        )
    }

    private fun generateBlackjackCard(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_A),
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_K),
                ),
            ),
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_5),
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_4),
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_3),
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_2),
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_A),
                    BlackJackCard(CardType.CLOVER, CardNumber.CARD_A),
                    BlackJackCard(CardType.CLOVER, CardNumber.CARD_5),
                ),
            ),
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_A),
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_K),
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_J),
                ),
            ),
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_A),
                    BlackJackCard(CardType.CLOVER, CardNumber.CARD_A),
                    BlackJackCard(CardType.SPADE, CardNumber.CARD_9),
                ),
            ),
        )
    }
}
