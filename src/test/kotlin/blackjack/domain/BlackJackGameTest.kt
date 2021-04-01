package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlackJackGameTest {

    @Test
    fun `게임을 시작하면 전달된 플레이어 이름 갯수 만큼 플레이어를 생성한다`() {
        assertThat(BlackJackGame.startGame(listOf("a", "b")).size).isEqualTo(2)
    }
}
