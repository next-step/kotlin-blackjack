package step2.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `소유한 카드를 특정 구분자로 합쳐진 카드 정보를 반환한다`() {
        // given
        val player = Player("test")
        player.draw(2)

        // when
        val result = player.getJoiningCardNames(",")

        //then
        assertThat(result).isNotBlank()
    }
}
