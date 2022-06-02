package game.blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

@DisplayName("플레이어")
internal class PlayerTest {

    @Test
    fun `이름으로 플레이어 생성`() {
        val expectedName = "jade"
        val card = Player(expectedName)
        assertThat(card.name).isEqualTo(expectedName)
    }

    @Test
    fun `y 입력하면 상태 그대로`() {
    }

    @Test
    fun `n 입력하면 상태 변화`() {
    }

    @Test
    fun `카드 입력하면 추가`() {
    }

    @Test
    fun `카드 받고 21 넘으면 상태 변화`() {
    }
}