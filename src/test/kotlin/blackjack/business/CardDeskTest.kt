package blackjack.business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("카드팩")
class CardDeskTest {

    @Test
    fun `한벌의 카드를 가지고 있다`() {
        // given,when
        val cardDesk = CardDesk()

        // then
        cardDesk.cards.size shouldBe 52
        cardDesk.cards.toSet().size shouldBe 52
    }

    @Test
    fun `한벌의 카드에서 하나의 카드를 뽑는다`() {
        // given
        val cardSelectionStrategy = FixSelectionStrategy()
        val cardDesk = CardDesk(cardSelectionStrategy)

        // when
        val card = cardDesk.draw()

        // then
        card shouldBe Card(Suit.SPADE, Rank.ACE)
        cardDesk.cards.size shouldBe 51
        cardDesk.cards.contains(card) shouldBe false
    }

    @Test
    fun `카드를 추가할 수 있다`() {
        // given
        val cardDesk = CardDesk()

        // when
        cardDesk.addCards()

        // then
        cardDesk.cards.size shouldBe 104
    }

    @Test
    fun `카드가 모두 소진시 새로운 카드 한벌을 추가한다`() {
        // given
        val cardDesk = CardDesk()

        // when
        repeat(52) { cardDesk.draw() }

        // then
        cardDesk.cards.size shouldBe 52
    }
}
