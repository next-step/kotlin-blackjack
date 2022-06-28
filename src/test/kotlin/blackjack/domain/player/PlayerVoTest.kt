package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PlayerVoTest {

    @Test
    fun `PlayerVo 의 프로퍼티 값을 확인한다`() {
        val playerVo = PlayerVo("김성주", "카드들")
        val actualName: String = playerVo.name
        val actualCards: String = playerVo.cards

        val expectedName = "김성주"
        val expectedCards = "카드들"

        assertAll("프로퍼티 값들 확인", {
            assertThat(actualName).isEqualTo(expectedName)
            assertThat(actualCards).isEqualTo(expectedCards)
        })
    }
}
