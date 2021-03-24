package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    private val johnCards = Cards(
        arrayListOf(
            Card(Number.FIVE to Pattern.CLOVER),
            Card(Number.KING to Pattern.DIAMOND),
            Card(Number.ACE to Pattern.DIAMOND)
        )
    )

    @Test
    fun `카드가 21이상인지 확인`() {
        val player = Player("John", johnCards )
        assertThat(player.checkMyCardsIsOver21()).isFalse()
    }
}