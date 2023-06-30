package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardHold
import blackjack.domain.card.CardRank
import blackjack.domain.card.CardShape
import blackjack.domain.card.Deck
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
    fun `플레이어는 돈을 베팅할 수 있다`() {
        // given
        val player = GamePlayer("player")
        val dealer = Dealer()
        dealer.getMoneyFromPlayer(player, 10000)

        // when
        val money = player.getBettingMoney()

        // then
        money.value shouldBe 10000
    }

    @Test
    fun `플레이어에게 돈을 돌려줄 수 있다`() {
        // given
        val player = GamePlayer("player")
        val dealer = Dealer()
        dealer.getMoneyFromPlayer(player, 10000)

        // when
        dealer.giveMoneyToPlayer(player)

        // then
        dealer.money.value shouldBe -10000
        player.money.value shouldBe 10000
    }

    @Test
    fun `플레이어에게 돈을 150프로 추가로 돌려준다`() {
        // given
        val player = GamePlayer("player")
        val dealer = Dealer()
        dealer.getMoneyFromPlayer(player, 10000)

        // when
        dealer.giveMoneyToPlayerInSpecial(player)

        // then
        dealer.money.value shouldBe -15000
        player.money.value shouldBe 15000
    }
}
