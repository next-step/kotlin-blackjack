package blackjack.business.card

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `모든 카드를 52장이고 서로 다른 카드인지 확인한다`() {
        // given
        val allCards = Card.allCards

        // when
        val distinctCards = allCards.distinct()

        // then
        allCards.size shouldBe 52
        distinctCards.size shouldBe 52
    }

    @Test
    fun `카드를 가져온다`() {
        // given,when
        val actual = Card.of(Suit.SPADE, Rank.ACE)

        // then
        actual.suit shouldBe Suit.SPADE
        actual.rank shouldBe Rank.ACE
    }
}
