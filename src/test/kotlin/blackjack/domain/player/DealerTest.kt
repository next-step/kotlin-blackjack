package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.player.state.BlackJack
import blackjack.domain.player.state.BlackJackTest.Companion.TEST_BLACKJACK
import blackjack.domain.player.state.BlackJackTest.Companion.blackJackCards
import blackjack.domain.player.state.Hit
import blackjack.domain.player.state.HitTest.Companion.TEST_HIT
import blackjack.domain.player.state.HitTest.Companion.hitCards
import blackjack.domain.player.state.Ready
import blackjack.domain.player.state.Stay
import blackjack.domain.player.state.StayTest.Companion.DEALER_STAY
import blackjack.domain.player.state.StayTest.Companion.dealerStayCards
import blackjack.domain.player.state.hands.Hands
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("게임 플레이어(GamePlayer)")
internal class DealerTest {

    @Test
    fun `디폴트 이름과 상태로 생성가능하다`() {
        val dealer: Player = Dealer()

        assertAll(
            { assertThat(dealer).isNotNull },
            { assertThat(dealer).isExactlyInstanceOf(Dealer::class.java) }
        )
    }

    @Test
    fun `카드 전략에 따라 드로우를 할 수 있다`() {
        val extraCard = Deck.initialize { it }.pop()
        val expected = Dealer(playerState = Ready().draw(extraCard))

        val dealer = Dealer().draw(Deck.initialize { it }) { listOf(it.pop()) }

        assertThat(dealer).isEqualTo(expected)
    }

    @Test
    fun `처음 뽑은 카드들이 21이면, BlackJack이다`() {
        val expected = Dealer(playerState = TEST_BLACKJACK)

        val dealer = Dealer().draw(Deck.initialize { it }) { blackJackCards() }

        assertAll(
            { assertThat(dealer.playerState is BlackJack).isTrue },
            { assertThat(dealer).isEqualTo(expected) },
        )
    }

    @Test
    fun `처음 뽑은 카드들이 16이하면, Hit이다`() {
        val expected = Dealer(playerState = TEST_HIT)

        val dealer = Dealer().draw(Deck.initialize { it }) { hitCards() }

        assertAll(
            { assertThat(dealer.playerState is Hit).isTrue },
            { assertThat(dealer).isEqualTo(expected) },
        )
    }

    @Test
    fun `처음 뽑은 카드들이 17이상이면, Stay 이다`() {
        val expected = Dealer(playerState = DEALER_STAY)

        val dealer = Dealer().draw(Deck.initialize { it }) { dealerStayCards() }

        assertAll(
            { assertThat(dealer.playerState is Stay).isTrue },
            { assertThat(dealer).isEqualTo(expected) },
        )
    }

    @Test
    fun `나중에 뽑은 카드들까지의 합이 21이하 17이상이면, Stay 이다`() {
        val expectedStayCards = hitCards() + Card(Suit.CLUB, Denomination.FOUR)
        val expected = Dealer(playerState = Stay(Hands.from(expectedStayCards)))

        val dealer = Dealer().draw(Deck.initialize { it }) { expectedStayCards }

        assertAll(
            { assertThat(dealer.playerState is Stay).isTrue },
            { assertThat(dealer).isEqualTo(expected) },
        )
    }
}
