package blackjack.domain.card

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardHoldTest {
    @Test
    fun `카드들의 합을 계산할 수 있다`() {
        val cloverEight = Card.createCard(CardRank.EIGHT, CardShape.CLOVER)
        val heartJack = Card.createCard(CardRank.JACK, CardShape.HEART)
        val cardHold = CardHold()
        cardHold.add(cloverEight)
        cardHold.add(heartJack)

        cardHold.getTotalPoints() shouldBe 18
    }
}
