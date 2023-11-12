package blackjack.business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("플레이어")
class PlayerTest {
    @Test
    fun `플레이어의 카드를 추가한다`() {
        // given
        val player = Player("pobi")

        // when
        player.addCard(Card(Suit.SPADE, Rank.ACE))

        // then
        player.cards.size shouldBe 1
        player.cards[0] shouldBe Card(Suit.SPADE, Rank.ACE)
    }
}
