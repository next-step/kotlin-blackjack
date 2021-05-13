package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameCardsTest {

    @Test
    fun `참가자에게 첫 카드를 전달하기 위해 2장의 카드를 뽑는다`() {
        val gameCards = GameCards()

        val cards = gameCards.pollCardsToFirstDraw()

        assertThat(cards).hasSize(2)
    }
}
