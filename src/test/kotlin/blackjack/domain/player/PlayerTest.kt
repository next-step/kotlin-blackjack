package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardHold
import blackjack.domain.card.CardRank
import blackjack.domain.card.CardShape
import blackjack.domain.card.Deck
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 처음 포인트가 0이다`() {
        // given
        val player = Player("goofy")
        // when
        player.getPoints() shouldBe 0
    }

    @Test
    fun `플레이어는 카드를 뽑을 수 있다`() {
        // given
        val player = Player("goofy")
        val deck = Deck()
        // when
        player.drawCard(deck)
        // then
        player.cardHold.getCardsTotalSize() shouldBe 1
        player.getPoints() shouldNotBe 0
    }

    @Test
    fun `21이 넘는 경우 카드를 뽑을 수 없다`() {
        val sampleCard = Card.createCard(CardRank.JACK, CardShape.CLOVER)
        val sampleCard2 = Card.createCard(CardRank.QUEEN, CardShape.HEART)
        val sampleCard3 = Card.createCard(CardRank.KING, CardShape.DIAMOND)
        val myCards = CardHold(listOf(sampleCard, sampleCard2, sampleCard3))
        val player = Player("goofy", myCards)

        // when
        val result = player.canDraw()

        // then
        result shouldBe false
    }

    @Test
    fun `21이 넘는 경우 ACE 카드는 포인트 1 취급을 받는다`() {
        val sampleCard = Card.createCard(CardRank.JACK, CardShape.CLOVER)
        val sampleCard2 = Card.createCard(CardRank.QUEEN, CardShape.HEART)
        val sampleCard3 = Card.createCard(CardRank.KING, CardShape.DIAMOND)
        val sampleCard4 = Card.createCard(CardRank.ACE, CardShape.HEART)
        val myCards = CardHold(listOf(sampleCard, sampleCard2, sampleCard3, sampleCard4))
        val player = Player("goofy", myCards)

        // when
        val result = player.getPoints()

        // then
        result shouldBe 31
    }
}
