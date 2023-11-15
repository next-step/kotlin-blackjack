package blackjack.model

import blackjack.dto.Card
import blackjack.dto.Number
import blackjack.dto.PlayerStatus
import blackjack.dto.Suit
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class DealerTest {

    @Test
    fun `카드 딜링 테스트`() {
        val dealer = Dealer()
        val cards = dealer.dealingTwoCards()
        assertAll(
            { assertThat(cards).hasSize(2) },
            { assertThat(cards[0]).isNotEqualTo(cards[1]) }
        )
    }

    @Test
    fun `카드가 다 소진되면 에러가 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            val dealer = Dealer()
            repeat(26) { dealer.dealingTwoCards() }
        }
    }

    @Test
    fun `딜러의 이름은 항상 딜러이다`() {
        val dealer = Dealer()
        assertThat(dealer.name).isEqualTo("딜러")
    }

    @Test
    fun `초기 dealing 카드 합이 17 이상이면 더이상 카드를 받지 않는다`() {
        val dealer = Dealer()
        dealer.addCards(listOf(Card(Suit.SPADE, Number.QUEEN), Card(Suit.SPADE, Number.SEVEN)))
        val hit = dealer.moreCard()
        assertAll(
            { assertThat(hit).isFalse() },
            { assertThat(dealer.cards).hasSize(2) },
            { assertThat(dealer.status).isEqualTo(PlayerStatus.STAY) }
        )
    }

    @Test
    fun `초기 dealing 카드 합이 16 이하이면 카드를 한 장 더 받는다`() {
        val dealer = Dealer()
        dealer.addCards(listOf(Card(Suit.SPADE, Number.QUEEN), Card(Suit.SPADE, Number.SIX)))
        val hit = dealer.moreCard()
        assertAll(
            { assertThat(hit).isTrue() },
            { assertThat(dealer.cards).hasSize(3) },
            { assertThat(dealer.status).isNotEqualTo(PlayerStatus.HIT) }
        )
    }

    @Test
    fun `초기 카드 dealing을 받으면 무조건 두 장의 카드를 소지하고 있다`() {
        val dealer = Dealer()
        dealer.initialCardDealing()
        assertThat(dealer.cards).hasSize(2)
    }

    @Test
    fun `플레이어와 승패를 결정 지으면 점수가 계산된다`() {
        // given
        val dealer = Dealer()
        val players = Players(listOf(Player("a"), Player("b")))
        dealer.addCards(listOf(Card(Suit.SPADE, Number.QUEEN), Card(Suit.SPADE, Number.SIX)))
        players.values[0].addCards(dealer.dealingTwoCards())
        players.values[1].addCards(dealer.dealingTwoCards())

        // when
        dealer.compareWithPlayers(players)

        // then
        assertThat(dealer.gameResult?.point).isEqualTo(16)
    }
}
