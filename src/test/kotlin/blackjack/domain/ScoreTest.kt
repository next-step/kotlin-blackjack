package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ScoreTest{
    @Test
    fun `카드의 점수를 카운팅한다`() {
        val cards = CardsTest.FakeCards.cards
        Score.countingCard(cards) shouldBe 21
    }

    @Test
    fun `Ace 카드는 카드의 점수가 21이 넘으면 1로 21이 넘지 않으면 11로 계산된다`() {
        val cards = Cards(listOf(Card(CardNumber.ACE, CardShape.DIAMOND)))
        Score.countingCard(cards) shouldBe 11
        cards.add(Card(CardNumber.ACE, CardShape.HEART))
        Score.countingCard(cards) shouldBe 12
        cards.add(Card(CardNumber.ACE, CardShape.SPADE))
        Score.countingCard(cards) shouldBe 13
    }
}
