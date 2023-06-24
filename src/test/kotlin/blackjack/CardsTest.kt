package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

/**
 * 카드를 랜덤하게 가져오는 로직(cards.getRandomCard())에 대한
 * 테스트를 구현하고싶은데 잘 되지 않아서 우선 skip합니다
 */
class CardsTest {
    @Test
    fun `최초 생성시 보유한 카드의 score가 함께 업데이트가 된다`() {
        val cards = Cards.from(
            Card(Denomination.ACE, CardType.CLUBS),
            Card(Denomination.JACK, CardType.DIAMONDS),
        )

        cards.getOptimizedScore() shouldBe 21
    }

    @Test
    fun `value의 반환 타입은 List 이다`() {
        val cards = Cards.from(
            Card(Denomination.ACE, CardType.CLUBS),
            Card(Denomination.JACK, CardType.DIAMONDS),
        )

        cards.getValue().shouldBeInstanceOf<List<Card>>()
    }

    @Test
    fun `value에 1장의 카드를 더할 수 있다`() {
        val cards = Cards()
        val card = Card(Denomination.ACE, CardType.CLUBS)

        cards.addCard(card)

        with(cards.getValue()) {
            size shouldBe 1
            this.first() shouldBe card
        }
    }

    @Test
    fun `value에 여러장의 카드를 더할 수 있다`() {
        val cards = Cards()
        val addCards = Cards.from(
            Card(Denomination.ACE, CardType.CLUBS),
            Card(Denomination.JACK, CardType.DIAMONDS),
        )

        cards.addCards(addCards)

        with(cards.getValue()) {
            size shouldBe 2
            this[0] shouldBe addCards.getValue()[0]
            this[1] shouldBe addCards.getValue()[1]
        }
    }

    @Test
    fun `ACE 카드가 있는 경우 가장 승리에 유리한 최적의 score를 계산한다`() {
        val cards = Cards()
        val addCards = Cards.from(
            Card(Denomination.ACE, CardType.CLUBS),
            Card(Denomination.ACE, CardType.DIAMONDS),
            Card(Denomination.ACE, CardType.HEARTS),
            Card(Denomination.ACE, CardType.SPADES),
        )

        cards.updateScoreSet(addCards)

        cards.getOptimizedScore() shouldBe 14
    }
}
