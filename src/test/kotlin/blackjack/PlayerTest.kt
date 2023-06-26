package blackjack

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = ["pobi", "jason"])
    fun `Player 객체에 이름을 부여할 수 있다`(name: String) {
        val player = Player(name)
        player.name shouldBe name
    }
}
