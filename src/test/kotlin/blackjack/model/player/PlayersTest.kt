package blackjack.model.player

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("플레이어 컬렉션 테스트")
class PlayersTest {

    @Test
    fun `플레이어가 1명 미만이면 예외 발생`() {
        // when, then
        val exception = assertThrows<IllegalArgumentException> { Players(listOf()) }
        Assertions.assertThat(exception.message).isEqualTo("플레이어는 1명 이상이어야 합니다.")
    }
}
