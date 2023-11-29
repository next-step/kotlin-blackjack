package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {
    @Test
    fun `21점을 넘지 않으면 플레이어는 게임을 계속할 수 있다`() {
        // given
        val player = Player("test")
        val cardList = CardList()
        val cards = listOf(
            Card(Suit.SPADE, Rank.ACE),
            Card(Suit.DIAMOND, Rank.NINE),
            Card(Suit.HEART, Rank.TWO)
        )
        cardList.addCard(cards)

        // when
        val isContinue = player.canDraw()

        // then
        assertThat(isContinue).isTrue()
    }
}
