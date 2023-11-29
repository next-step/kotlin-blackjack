package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RankTest {

    @Test
    fun `Rank는 1부터 10까지의 점수를 갖는다`() {
        // given, when
        val rankList = Rank.values().map { it.score }.sorted()

        // then
        assertThat(rankList.first()).isEqualTo(1)
        assertThat(rankList.last()).isEqualTo(10)
    }
}
