package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `{given} 플레이어들의 이름 {when} initPlayers {then} 각 플에이어들을 초기화 한다`() {
        // given
        val playerNames = listOf("Sara", "John", "Levi")
        val dealer = Dealer(drawCard = { Card(Rank.TWO, Suit.HEARTS) })

        // when
        val players =
            dealer.initPlayers(
                fetchPlayerNames = { playerNames },
                onPlayerInit = {},
            )

        // then
        assertThat(players.value[0]).hasFieldOrPropertyWithValue("name", "Sara")
        assertThat(players.value[1]).hasFieldOrPropertyWithValue("name", "John")
        assertThat(players.value[2]).hasFieldOrPropertyWithValue("name", "Levi")
    }

    @Test
    fun `{given} 딜러의 카드 합이 16 이하일 때 {when & then} 카드를 한개 더 뽑아 총 3장 됨`() {
        // given
        val dealer = Dealer(drawCard = { Card(Rank.TWO, Suit.HEARTS) })
        assertThat(dealer.isAddCardEnabled()).isTrue()

        // when
        dealer.drawOneMoreCardIfNeeded {}

        // then
        assertThat(dealer.cards.value.size).isEqualTo(3)
    }

    @Test
    fun `{given} 딜러 생성 {when} getCardForInitialDisplay() {then} 갖고 있는 첫번째 카드를 반환`() {
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
}
