package blackjack

import blackjack.CardTest.Card
import blackjack.CardTest.Symbol
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    internal fun `플레이어는 받은 카드 목록이 있다`() {
        val player = Player()
            .apply {
                draw(Card("2", Symbol.HEARTS, 2))
                draw(Card("8", Symbol.SPADES, 8))
            }
        assertThat(player.cards).contains(
            Card("2", Symbol.HEARTS, 2),
            Card("8", Symbol.SPADES, 8)
        )
    }
}
