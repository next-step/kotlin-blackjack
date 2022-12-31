package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GamersTest {

    @Test
    fun `게이머들의 게임이 모두 끝났다`() {
        // given
        val gamers = Gamers(
            listOf(
                Gamer("younho", isFinish = true),
                Gamer("junyoung", isFinish = true),
                Gamer("jiseok", isFinish = true)
            )
        )

        // when
        val actual = gamers.isFinish()

        // then
        assertThat(actual).isTrue
    }

    @Test
    fun `게임이 끝나지 않은 게이머가 있다`() {
        // given
        val gamers = Gamers(
            listOf(
                Gamer("younho", isFinish = true),
                Gamer("junyoung", isFinish = false),
                Gamer("jiseok", isFinish = true)
            )
        )

        // when
        val actual = gamers.isFinish()

        // then
        assertThat(actual).isFalse
    }
}
