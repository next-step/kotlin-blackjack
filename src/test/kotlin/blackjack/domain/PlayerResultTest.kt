@file:Suppress("NonAsciiCharacters")

package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerResultTest {
    @Test
    fun `플레이어의 점수가 딜러의 점수보다 높으면 플레이어가 승리한다`() {
        val playerScore = 11
        val dealerScore = 10

        val actual = PlayerResult.of(playerScore, dealerScore)

        assertThat(actual).isEqualTo(PlayerResult.WIN)
    }

    @Test
    fun `플레이어의 점수가 딜러의 점수와 같으면 무승부이다`() {
        val playerScore = 10
        val dealerScore = 10

        val actual = PlayerResult.of(playerScore, dealerScore)

        assertThat(actual).isEqualTo(PlayerResult.DRAW)
    }

    @Test
    fun `플레이어의 점수가 딜러의 점수보다 낮으면 플레이어가 패배한다`() {
        val playerScore = 10
        val dealerScore = 11

        val actual = PlayerResult.of(playerScore, dealerScore)

        assertThat(actual).isEqualTo(PlayerResult.LOSE)
    }
}
