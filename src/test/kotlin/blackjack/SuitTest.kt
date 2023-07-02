package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SuitTest {
    @Test
    internal fun `카드이름에는 문양이 포함되어 있다`() {
        data class TestCase(
            val cardString: String,
            val expected: Suit,
        )

        val testList = listOf(
            TestCase("A스페이드", Suit.SPADE),
            TestCase("A하트", Suit.HEART),
            TestCase("A클로버", Suit.CLUB),
            TestCase("A다이아", Suit.DIAMOND),
        )
        testList.shouldForAll { testCase ->
            Suit.of(testCase.cardString) shouldBe testCase.expected
        }
    }

    @Test
    internal fun `카드이름에 문양이 포함되어 있지 않으면 에러 발생`() {
        shouldThrow<IllegalArgumentException> {
            Suit.of("A스페이")
        }
    }
}
