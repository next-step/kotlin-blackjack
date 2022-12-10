package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @DisplayName("참여자는 이름을 가지고 있다")
    @Test
    fun hasName() {
        val player = Player("hjw")

        player.name shouldBe "hjw"
    }
}
