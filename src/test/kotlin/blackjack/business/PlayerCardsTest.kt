package blackjack.business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerCardsTest {

    @Test
    fun `카드를 추가한다`() {
        // given
        val playerCards = PlayerCards()

        // when
        playerCards.add(Card(Suit.SPADE, Rank.ACE))

        // then
        playerCards.size shouldBe 1
        playerCards[0] shouldBe Card(Suit.SPADE, Rank.ACE)
    }
}
