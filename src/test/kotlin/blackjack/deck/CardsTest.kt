package blackjack.deck

import blackjack.card.CardFixture
import blackjack.card.Rank
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardsTest {
    private lateinit var cards: Cards

    @Test
    fun draw() {
        val initCards = List(size = 3) { CardFixture.generateTestCard(rank = Rank.SIX) }
        cards = Cards(cards = initCards)
        val drawCard = CardFixture.generateTestCard(rank = Rank.SIX)
        cards.draw(drawCard) shouldBe Cards(cards = initCards - drawCard)
    }

    @Test
    fun `목록에 없는 카드를 뽑는 경우 null을 반환한다`() {
        cards = Cards(cards = List(size = 3) { CardFixture.generateTestCard(rank = Rank.SIX) })

        cards.draw(CardFixture.generateTestCard(rank = Rank.TEN)) shouldBe null
    }
}
