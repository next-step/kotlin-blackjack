package blackjack.domain

import blackjack.view.ResultView
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
        numbers: List<Int>,
        points: Int,
    ) {
        val user = GameUser("홍길동")
        numbers.forEach {
            user.addCard(BlackJackCard(it))
        }
        ResultView().printResultCards(listOf(user))
        user.points shouldBe points
    }

    @ParameterizedTest
    @MethodSource("generateBlackjackCard")
    fun `블랙잭이 맞는지 확인`(numbers: List<Int>) {
        val user = GameUser("홍길동")
        numbers.forEach {
            user.addCard(BlackJackCard(it))
        }
        ResultView().printResultCards(listOf(user))
        user.points shouldBe 21
    }

    private fun generateCardNumbers(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(listOf(1, 2, 3), 16),
            Arguments.of(listOf(11, 12, 13), 30),
            Arguments.of(listOf(14, 15, 16), 16),
            Arguments.of(listOf(27, 28, 29), 16),
            Arguments.of(listOf(40, 41, 42), 16),
            Arguments.of(listOf(50, 52, 51), 30),
            Arguments.of(listOf(1, 13), 21),
        )
    }

    private fun generateBlackjackCard(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(listOf(1, 13)),
            Arguments.of(listOf(5, 4, 3, 2, 1, 14, 18)),
            Arguments.of(listOf(1, 13, 11)),
            Arguments.of(listOf(1, 14, 9)),
        )
    }
}
