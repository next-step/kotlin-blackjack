package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `플레이어는 이름과 2장의 카드를 처음에 가진다`() {
        val player = Player(
            "pobi", Card.diamond(Number.EIGHT), Card.heart(Number.TEN)
        )

        assertThat(player.name).isEqualTo("pobi")
        assertThat(player.cards).isEqualTo(
            Cards(Card.diamond(Number.EIGHT), Card.heart(Number.TEN))
        )
    }
}
