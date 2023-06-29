package blackjack

import blackjack.domain.GameCardsSet
import blackjack.domain.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 카드들을 가질 수 있다`() {
        val gameCardsSet = GameCardsSet()
        val player1 = Player("사람1", gameCardsSet)
        player1.hit()
        assertThat(player1.numberOfMyCards()).isEqualTo(1)
    }
}
