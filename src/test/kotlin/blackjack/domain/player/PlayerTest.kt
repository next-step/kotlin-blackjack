package blackjack.domain.player

import blackjack.domain.createCard
import blackjack.domain.createPlayer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class PlayerTest {
    @DisplayName("플레이어가 카드를 인자로 받은 경우 카드 목록에 추가")
    @Test
    fun draw() {
        val player = createPlayer("pobi")
        val card = createCard("ACE", "SPADE")

        player.draw(card)

        assertThat(player.hands).isEqualTo(listOf(card))
    }
}
