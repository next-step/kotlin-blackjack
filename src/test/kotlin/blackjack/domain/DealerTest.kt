package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class DealerTest {
    @Test
    @DisplayName("플레이어들의 이름으로 Player 들을 초기화")
    fun `should initialize players with given names`() {
        // given
        val playerNames = listOf("Sara", "John", "Levi")
        val dealer = Dealer(drawCard = { Card(Rank.TWO, Suit.HEARTS) })

        // when
        val players =
            dealer.initPlayers(
                fetchPlayerNames = { playerNames },
                getBettingAmount = { BigDecimal.ZERO },
                onPlayerInit = {},
            )

        // then
        assertThat(players.value[0]).hasFieldOrPropertyWithValue("name", "Sara")
        assertThat(players.value[1]).hasFieldOrPropertyWithValue("name", "John")
        assertThat(players.value[2]).hasFieldOrPropertyWithValue("name", "Levi")
    }

    @Test
    @DisplayName("딜러의 카드 합이 16 이하일 때 한 장 더 뽑기")
    fun `should draw one more card when dealer's card sum is 16 or less`() {
        // given
        val dealer = Dealer(drawCard = { Card(Rank.TWO, Suit.HEARTS) })
        assertThat(dealer.isAddCardEnabled()).isTrue()

        // when
        dealer.drawOneMoreCardIfNeeded {}

        // then
        assertThat(dealer.cards.value.size).isEqualTo(3)
    }

    @Test
    @DisplayName("딜러가 가진 카드 중 하나만 꺼내기")
    fun `should return the first card when dealer retrieves one card for initial display`() {
        // given
        val firstCard = Card(Rank.TWO, Suit.HEARTS)
        val secondCard = Card(Rank.TWO, Suit.HEARTS)
        val cards = ArrayDeque(listOf(firstCard, secondCard))
        val dealer = Dealer(drawCard = { cards.removeFirst() })

        // when
        val card = dealer.getCardForInitialDisplay()

        // then
        assertThat(card).isSameAs(firstCard)
    }

    @Test
    @DisplayName("플레이어가 10000원 만큼 얻었다면 딜러는 최초 총 베팅 금액에서 10000원을 차감한 금액을 갖는다")
    fun `when player gains 10000, dealer loses 10000`() {
        // given
        val dealer = Dealer(drawCard = { Card(Rank.TWO, Suit.HEARTS) })
        val totalBet = BigDecimal(30000)
        dealer.profitMoney.set(totalBet)

        // when
        dealer.adjustProfit(playerProfit = ProfitMoney().apply { set(BigDecimal(10000)) })

        // then
        assertThat(dealer.profitMoney.getCurrentProfit()).isEqualTo(BigDecimal(20000))
    }
}
