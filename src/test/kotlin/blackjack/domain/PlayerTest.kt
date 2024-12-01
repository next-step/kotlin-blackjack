package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class PlayerTest {
    @Test
    fun `이름이 빈 문자열인 경우 예외를 던진다`() {
        assertThrows<IllegalArgumentException> { Player("") }
    }

    @Test
    fun `이름을 지정할 수 있다`() {
        val player = Player("jack")
        player.name shouldBe "jack"
    }

    @Test
    fun `플레이어 생성 시 카드가 없다`() {
        val player = Player("jack")
        player.value shouldBe 0
    }
}
