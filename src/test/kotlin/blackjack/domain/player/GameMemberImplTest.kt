package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardHold
import blackjack.domain.card.CardRank
import blackjack.domain.card.CardShape
import blackjack.domain.card.Deck
import blackjack.domain.rule.Money
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

class GameMemberImplTest {
    @Test
    fun `플레이어는 처음 포인트가 0이다`() {
        // given
        val gamePlayer = GamePlayer("goofy")
        // when
        gamePlayer.getPoints() shouldBe 0
    }

    @Test
    fun `플레이어는 카드를 뽑을 수 있다`() {
        // given
        val gamePlayer = GamePlayer("goofy")
        val deck = Deck()
        // when
        gamePlayer.drawCard(deck)
        // then
        gamePlayer.cardHold.getCardsTotalSize() shouldBe 1
        gamePlayer.getPoints() shouldNotBe 0
    }

    @Test
    fun `21이 넘는 경우 카드를 뽑을 수 없다`() {
        val sampleCard = Card.createCard(CardRank.JACK, CardShape.CLOVER)
        val sampleCard2 = Card.createCard(CardRank.QUEEN, CardShape.HEART)
        val sampleCard3 = Card.createCard(CardRank.KING, CardShape.DIAMOND)
        val myCards = CardHold(mutableListOf(sampleCard, sampleCard2, sampleCard3))
        val gamePlayer = GamePlayer("goofy", myCards)

        // when
        val result = gamePlayer.canDraw()

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
        val gamePlayer = GamePlayer("goofy", myCards)

        // when
        val result = gamePlayer.getPoints()

        // then
        result shouldBe 31
    }

    @Test
    fun `게임 플레이어는 돈을 낼 수 있다`() {
        // given
        val bettingMoney = 10000
        val player = GamePlayer("name")

        // when
        val money = player.betMoney(bettingMoney)

        // then
        money.value shouldBe bettingMoney
        player.money.value shouldBe -10000
    }

    @Test
    fun `게임플레이어는 돈을 돌려받을 수 있다`() {
        // given
        val winMoney = Money(10000)
        val player = GamePlayer("name")

        // when
        player.winMoney(winMoney)

        // then
        player.money.value shouldBe winMoney.value
    }
}
