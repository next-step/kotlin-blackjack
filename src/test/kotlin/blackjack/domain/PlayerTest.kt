package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Player(val name: String)

class PlayerTest {

    @Test
    fun `플레이어는 이름을 가질 수 있다`() {
        val name = "홍길동"
        val player = Player(name)

        player.name shouldBe name
    }
}
