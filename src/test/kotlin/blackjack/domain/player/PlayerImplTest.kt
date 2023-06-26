package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardHold
import blackjack.domain.card.CardRank
import blackjack.domain.card.CardShape
import blackjack.domain.card.Deck
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerImplTest {
    @Test
    fun `플레이어는 처음 포인트가 0이다`() {
        // given
        val playerImpl = PlayerImpl("goofy")
        // when
        playerImpl.getPoints() shouldBe 0
    }

    @Test
    fun `플레이어는 카드를 뽑을 수 있다`() {
        // given
        val playerImpl = PlayerImpl("goofy")
        val deck = Deck()
        // when
        playerImpl.drawCard(deck)
        // then
        playerImpl.cardHold.getCardsTotalSize() shouldBe 1
        playerImpl.getPoints() shouldNotBe 0
    }

    @Test
    fun `21이 넘는 경우 카드를 뽑을 수 없다`() {
        val sampleCard = Card.createCard(CardRank.JACK, CardShape.CLOVER)
        val sampleCard2 = Card.createCard(CardRank.QUEEN, CardShape.HEART)
        val sampleCard3 = Card.createCard(CardRank.KING, CardShape.DIAMOND)
        val myCards = CardHold(mutableListOf(sampleCard, sampleCard2, sampleCard3))
        val playerImpl = PlayerImpl("goofy", myCards)

        // when
        val result = playerImpl.canDraw()

        // then
        result shouldBe false
    }

    @Test
    fun `21이 넘는 경우 ACE 카드는 포인트 1 취급을 받는다`() {
        val sampleCard = Card.createCard(CardRank.JACK, CardShape.CLOVER)
        val sampleCard2 = Card.createCard(CardRank.QUEEN, CardShape.HEART)
        val sampleCard3 = Card.createCard(CardRank.KING, CardShape.DIAMOND)
        val sampleCard4 = Card.createCard(CardRank.ACE, CardShape.HEART)
        val myCards = CardHold(mutableListOf(sampleCard, sampleCard2, sampleCard3, sampleCard4))
        val playerImpl = PlayerImpl("goofy", myCards)

        // when
        val result = playerImpl.getPoints()

        // then
        result shouldBe 31
    }

    @Test
    fun `플레이어간에는 점수 비교를 하지 않는다`() {
        // given player 1
        val sampleCard = Card.createCard(CardRank.JACK, CardShape.CLOVER)
        val sampleCard2 = Card.createCard(CardRank.QUEEN, CardShape.HEART)
        val myCards = CardHold(mutableListOf(sampleCard, sampleCard2))
        val goofyPlayer = PlayerImpl("goofy", myCards)

        // given player 2
        val sampleCard3 = Card.createCard(CardRank.QUEEN, CardShape.HEART)
        val myCards2 = CardHold(mutableListOf(sampleCard3))
        val zeroPlayer = PlayerImpl("zero", myCards2)

        // when
        assertThrows<IllegalArgumentException> {
            zeroPlayer.compareScore(goofyPlayer)
        }
    }
}
