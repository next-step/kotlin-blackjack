package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @ValueSource(strings = ["pobi", "jason", "eden"])
    @ParameterizedTest
    fun `참가자는 이름을 가진다`(name: String) {
        Player(name).name shouldBe name
    }
}
