package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardHold
import blackjack.domain.card.CardRank
import blackjack.domain.card.CardShape
import blackjack.domain.card.Deck
import blackjack.domain.rule.Score
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 처음 포인트가 0이다`() {
        // given
        val dealer = Dealer()
        // when
        dealer.getPoints() shouldBe 0
    }

    @Test
    fun `딜러는 카드를 뽑을 수 있다`() {
        // given
        val dealer = Dealer()
        val deck = Deck()
        // when
        dealer.drawCard(deck)
        // then
        dealer.cardHold.getCardsTotalSize() shouldBe 1
        dealer.getPoints() shouldNotBe 0
    }

    @Test
    fun `포인트가 17 이상인 경우 카드를 뽑을 수 없다`() {
        val sampleCard = Card.createCard(CardRank.KING, CardShape.CLOVER)
        val sampleCard2 = Card.createCard(CardRank.SEVEN, CardShape.HEART)
        val myCards = CardHold(mutableListOf(sampleCard, sampleCard2))
        val dealer = Dealer(cardHold = myCards)

        // when
        val result = dealer.canDraw()

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
        val dealer = Dealer(cardHold = myCards)

        // when
        val result = dealer.getPoints()

        // then
        result shouldBe 31
    }

    @Test
    fun `딜러가 21 포인트가 되면 항상 이긴다`() {
        // given player 1
        val sampleCard = Card.createCard(CardRank.EIGHT, CardShape.CLOVER)
        val sampleCard2 = Card.createCard(CardRank.NINE, CardShape.HEART)
        val additionalCard = Card.createCard(CardRank.QUEEN, CardShape.DIAMOND)
        val additionalCard2 = Card.createCard(CardRank.QUEEN, CardShape.SPADE)
        val myCards = CardHold(mutableListOf(sampleCard, sampleCard2))
        val goofyPlayer = GamePlayer("goofy", myCards)

        // given dealer
        val sampleCard4 = Card.createCard(CardRank.JACK, CardShape.CLOVER)
        val sampleCard5 = Card.createCard(CardRank.QUEEN, CardShape.HEART)
        val sampleCard6 = Card.createCard(CardRank.KING, CardShape.HEART)
        val myCards3 = CardHold(mutableListOf(sampleCard4, sampleCard5, sampleCard6))
        val dealer = Dealer(cardHold = myCards3)

        // when
        val result = dealer.compareScore(goofyPlayer)
        // then
        result shouldBe Score().win()
    }
}
