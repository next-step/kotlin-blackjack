package blackjack.domain.card

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardHoldTest {
    @Test
    fun `카드들의 합을 계산할 수 있다`() {
        val cloverEight = Card.createCard(CardRank.EIGHT, CardShape.CLOVER)
        val heartJack = Card.createCard(CardRank.JACK, CardShape.HEART)
        val cardHold = CardHold()
            .add(cloverEight)
            .add(heartJack)

        cardHold.getPoints() shouldBe 18
    }

    @Test
    fun `Ace는 1 또는 11로 계산해서 최적의 값을 비교한다`() {
        val cloverAce = Card.createCard(CardRank.ACE, CardShape.CLOVER)
        val heartTen = Card.createCard(CardRank.TEN, CardShape.HEART)
        val cardHold = CardHold()
            .add(cloverAce)
            .add(heartTen)

        cardHold.getPoints() shouldBe 21
    }
}
