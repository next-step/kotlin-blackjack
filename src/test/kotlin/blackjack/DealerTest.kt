package blackjack

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.enums.CardPoint
import blackjack.domain.enums.CardShape
import blackjack.fixture.FixedCardFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 카드를 줄 수 있다`() {
        assertThat(Dealer().give() is Card).isEqualTo(true)
    }

    @Test
    fun `딜러가 처음주는 카드는 2 장이다`() {
        assertThat(Dealer().shareCards().size).isEqualTo(2)
    }

    @Test
    fun `딜러가 카드를 소지할 수 있다`() {
        val dealer: Player = Dealer()
        dealer.takeCard(FixedCardFactory(CardShape.HEART, CardPoint.KING).create())
        val card = dealer.cards[0]

        assertThat(Pair(card.shape, card.point)).isEqualTo(Pair(CardShape.HEART, CardPoint.KING))
    }

    @Test
    fun `딜러는 가진 카드의 합이 16 이하면 한 장의 카드를 받는다`() {
        val dealer = Dealer()
        dealer.takeCard(FixedCardFactory(CardShape.HEART, CardPoint.KING).create())
        dealer.takeCard(FixedCardFactory(CardShape.HEART, CardPoint.SIX).create())
        dealer.getMoreCard()

        assertThat(dealer.cards.size).isEqualTo(3)
    }

    @Test
    fun `딜러는 가진 카드의 합이 17 이상이면 카드를 받을 수 없다`() {
        val dealer = Dealer()
        dealer.takeCard(FixedCardFactory(CardShape.HEART, CardPoint.KING).create())
        dealer.takeCard(FixedCardFactory(CardShape.HEART, CardPoint.SEVEN).create())
        dealer.getMoreCard()

        assertThat(dealer.cards.size).isEqualTo(2)
    }

    @Test
    fun `딜러의 카드 합이 21을 초과하면 패배한다`() {
        val dealer = Dealer()
        dealer.takeCard(FixedCardFactory(CardShape.HEART, CardPoint.KING).create())
        dealer.takeCard(FixedCardFactory(CardShape.DIAMOND, CardPoint.KING).create())
        dealer.takeCard(FixedCardFactory(CardShape.HEART, CardPoint.TWO).create())
        val player = Player("정국")

        val result = dealer.winOrLose(listOf(dealer, player))

        assertThat(result).isEqualTo(false)
    }

    @Test
    fun `딜러는 플레이어 카드와 비교해 패배한 사실을 알 수 있다`() {
        val dealer = Dealer()
        dealer.takeCard(FixedCardFactory(CardShape.HEART, CardPoint.KING).create())
        dealer.takeCard(FixedCardFactory(CardShape.HEART, CardPoint.SEVEN).create())
        val winnerPlayer = Player("승리자")
        winnerPlayer.takeCard(FixedCardFactory(CardShape.DIAMOND, CardPoint.KING).create())
        winnerPlayer.takeCard(FixedCardFactory(CardShape.DIAMOND, CardPoint.QUEEN).create())

        val result = dealer.winOrLose(listOf(winnerPlayer))

        assertThat(result).isEqualTo(false)
    }

    @Test
    fun `딜러는 플레이어 카드와 비교해 이긴 사실을 알 수 있다`() {
        val dealer = Dealer()
        dealer.takeCard(FixedCardFactory(CardShape.HEART, CardPoint.KING).create())
        dealer.takeCard(FixedCardFactory(CardShape.HEART, CardPoint.SEVEN).create())
        val losePlayer = Player("패배자")
        losePlayer.takeCard(FixedCardFactory(CardShape.DIAMOND, CardPoint.TWO).create())
        losePlayer.takeCard(FixedCardFactory(CardShape.DIAMOND, CardPoint.QUEEN).create())

        val result = dealer.winOrLose(listOf(losePlayer))

        assertThat(result).isEqualTo(true)
    }
}
