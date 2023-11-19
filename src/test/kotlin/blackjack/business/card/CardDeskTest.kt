package blackjack.business.card

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("카드팩")
class CardDeskTest {

    @Test
    fun `카드가 모두 소진시 새로운 카드 한벌을 추가한다`() {
        // give
        val cardDesk = CardDesk()

        // when
        repeat(52) { cardDesk.draw() }

        // then
        cardDesk.cards.size shouldBe 52
    }

    @Test
    fun `시작시 카드를 두장씩 나눠준다`() {
        // given
        val cardDesk = CardDesk()

        // when
        val playerCards = cardDesk.startDraw()

        // then
        playerCards.size shouldBe 2
        cardDesk.cards.size shouldBe 50
    }
}
