package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    internal fun `카드이름에 Rank가 포함되어 있다`() {
        data class TestCase(
            val cardString: String,
            val expected: Rank,
        )

        val testList = listOf(
            TestCase("A스페이드", Rank.ACE),
            TestCase("2스페이드", Rank.TWO),
            TestCase("3스페이드", Rank.THREE),
            TestCase("4스페이드", Rank.FOUR),
            TestCase("5스페이드", Rank.FIVE),
            TestCase("6스페이드", Rank.SIX),
            TestCase("7스페이드", Rank.SEVEN),
            TestCase("8스페이드", Rank.EIGHT),
            TestCase("9스페이드", Rank.NINE),
            TestCase("10스페이드", Rank.TEN),
            TestCase("J스페이드", Rank.JACK),
            TestCase("Q스페이드", Rank.QUEEN),
            TestCase("K스페이드", Rank.KING),
        )

        testList.forEach { testCase ->
            Rank.of(testCase.cardString) shouldBe testCase.expected
        }
    }

    @Test
    internal fun `카드이름에 Rank가 포함되어 있지 않으면 error`() {
        shouldThrow<IllegalArgumentException> {
            Rank.of("B스페이드")
        }
    }
}
