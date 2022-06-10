package balckjac.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `플레이어는 이름과 카드 정보를 갖는다`() {
        Player(
            name = "justin",
            cards = listOf("justin")
        )
    }

    @Test
    fun `플레이어는 카드 정보를 추가할 수 있다`() {
        val player = Player(
            name = "justin",
            cards = listOf("card")
        )
        player.addCard(listOf("newCard"))

        assertThat(player.cards).contains("newCard")

    }
}
