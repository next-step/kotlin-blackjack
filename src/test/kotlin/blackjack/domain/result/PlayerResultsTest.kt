package blackjack.domain.result

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerResultsTest {
    @Test
    fun `딜러의 수익이 계산되어 있는지 확인`() {
        // given
        val playerResultList = listOf(
            PlayerResult("a", -40_000),
            PlayerResult("b", 10_000),
            PlayerResult("c", 5_000)
        )
        val expectedDealerResult = PlayerResult("딜러", 25_000)

        // when
        val playerResults = PlayerResults(playerResultList)

        // then
        assertThat(playerResults.values).contains(expectedDealerResult)
    }
}
