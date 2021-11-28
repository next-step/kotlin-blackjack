package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.player.name.Name
import blackjack.domain.player.state.BlackJack
import blackjack.domain.player.state.Hit
import blackjack.domain.player.state.MatchResult
import blackjack.domain.player.state.Ready
import blackjack.domain.player.state.Stay
import blackjack.domain.util.PlayerStateTestFixture
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("딜러(Dealer)")
internal class DealerTest {

    @Test
    fun `디폴트 이름과 상태로 생성가능하다`() {
        val dealer = Dealer()

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
        val dealer = Dealer()
            .draw(Deck.initialize { it }) {
                listOf(
                    Card(Suit.CLUB, Denomination.ACE), Card(Suit.CLUB, Denomination.JACK))
            }

        assertThat(dealer.playerState).isExactlyInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `처음 뽑은 카드들이 16이하면, Hit이다`() {
        val dealer = Dealer()
            .draw(Deck.initialize { it }) {
                listOf(Card(Suit.CLUB, Denomination.ACE), Card(Suit.CLUB, Denomination.TWO))
            }

        assertThat(dealer.playerState).isExactlyInstanceOf(Hit::class.java)
    }

    @Test
    fun `처음 뽑은 카드들이 17이상이면, Stay 이다`() {
        val dealer = Dealer()
            .draw(Deck.initialize { it }) {
                listOf(Card(Suit.CLUB, Denomination.TEN), Card(Suit.CLUB, Denomination.SEVEN))
            }

        assertThat(dealer.playerState).isExactlyInstanceOf(Stay::class.java)
    }

    @Test
    fun `나중에 뽑은 카드들까지의 합이 21이하 17이상이면, Stay 이다`() {
        val hit = Hit(PlayerStateTestFixture.createHands(Suit.CLUB, Denomination.TEN, Denomination.SIX))

        val dealer = Dealer(playerState = hit)
            .draw(Deck.initialize { it }) { listOf(Card(Suit.CLUB, Denomination.ACE)) }

        assertThat(dealer.playerState).isExactlyInstanceOf(Stay::class.java)
    }

    @Test
    fun `딜러가 버스트면, 딜러는 무조건 패한다`() {
        val hit = Hit(PlayerStateTestFixture.createHands(Suit.CLUB, Denomination.TEN, Denomination.SIX))

        val dealer = Dealer(playerState = hit)
            .draw(Deck.initialize { it }) { listOf(Card(Suit.CLUB, Denomination.JACK)) }

        val gamePlayer = GamePlayer(Name("user"), hit)
            .draw(Deck.initialize { it }) { listOf(Card(Suit.CLUB, Denomination.JACK)) }

        assertThat(dealer.match(gamePlayer)).isEqualTo(MatchResult.LOSE)
    }
}
