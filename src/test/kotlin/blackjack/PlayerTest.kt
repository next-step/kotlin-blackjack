package blackjack

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = ["pobi", "jason"])
    fun `Player 객체에 이름을 부여할 수 있다`(name: String) {
        val player = Player(name)
        player.name shouldBe name
    }

    @Test
    fun `쉼표 기준으로 분리된 이름을 받아 Player들을 생성한다`() {
        val nameList = listOf("pobi", "jason")
        val players = Players(nameList)

        nameList.all{ name ->
            players.players.any { player ->
                player.name == name
            }
        } shouldBe true
    }
}
