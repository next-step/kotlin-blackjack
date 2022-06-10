package balckjac.domain

import org.junit.jupiter.api.Test

internal class PlayersTest {

    @Test
    fun `플레이어 1급 콜렉션은 다수의 플레이어 정보를 갖는다`() {
        Players(
            players = listOf(Player("김일번"), Player("김이번"))
        )
    }
}
