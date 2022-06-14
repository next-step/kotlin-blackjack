package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("플레이어 테스트")
class PlayerTest {

    @Test
    fun `플레이어 생성시 기능이 정상 동작`() {
        // given, when, then
        assertThat(Player("aiden").name).isEqualTo("aiden")
    }

    @Test
    fun `플레이어 생성시 가지고 있는 카드 개수는 0개`() {
        // given, when, then
        assertThat(Player("aiden").cards.size).isEqualTo(0)
    }
}
