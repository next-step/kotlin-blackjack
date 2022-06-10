package balckjac.domain

import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `플레이어는 이름과 카드 정보를 갖는다`() {
        Player(
            name = "justin",
            cards = listOf("justin")
        )
    }
}
