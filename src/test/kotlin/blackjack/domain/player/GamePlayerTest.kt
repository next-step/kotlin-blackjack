package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.player.name.Name
import blackjack.domain.player.state.BlackJack
import blackjack.domain.player.state.Bust
import blackjack.domain.player.state.Hit
import blackjack.domain.player.state.MatchResult
import blackjack.domain.player.state.Ready
import blackjack.domain.player.state.hands.Hands
import blackjack.domain.util.PlayerStateTestFixture.BustFixture.CLUB_MINIMUM_BUST
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("게임 참가자(GamePlayer)")
internal class GamePlayerTest {

    @Test
    fun `이름과 상태로 생성가능하다`() {
        val gamePlayer = GamePlayer(Name("김우재"))

        assertAll(
            { assertThat(gamePlayer).isNotNull },
            { assertThat(gamePlayer).isExactlyInstanceOf(GamePlayer::class.java) }
        )
    }

    @Test
    fun `카드 전략에 따라 드로우를 할 수 있다`() {
        val extraCard = Deck.initialize { it }.pop()
        val expected = GamePlayer(Name("김우재"), Ready().draw(extraCard))

        val dealer = GamePlayer(Name("김우재")).draw(Deck.initialize { it }) { listOf(it.pop()) }

        assertThat(dealer).isEqualTo(expected)
    }

    @Test
    fun `처음 뽑은 카드들이 21이면, BlackJack이다`() {
        val gamePlayer = GamePlayer(Name("김우재")).draw(Deck.initialize { it }) {
            listOf(Card(Suit.CLUB, Denomination.ACE), Card(Suit.CLUB, Denomination.JACK))
        }

        assertThat(gamePlayer.playerState).isExactlyInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `처음 뽑은 카드들이 21미만이면, Hit이다`() {
        val gamePlayer = GamePlayer(Name("김우재")).draw(Deck.initialize { it }) {
            listOf(Card(Suit.CLUB, Denomination.ACE), Card(Suit.CLUB, Denomination.TWO))
        }

        assertThat(gamePlayer.playerState).isExactlyInstanceOf(Hit::class.java)
    }

    @Test
    fun `나중에 뽑은 카드들까지의 합이 21이하면, Hit 이다`() {
        val maximumHit = Hit(
            Hands.EMPTY
                .draw(Card(Suit.CLUB, Denomination.ACE))
                .draw(Card(Suit.CLUB, Denomination.TWO))
        )
        val gamePlayer = GamePlayer(Name("김우재"), maximumHit)
            .draw(Deck.initialize { it }) { listOf(Card(Suit.CLUB, Denomination.EIGHT)) }

        assertThat(gamePlayer.playerState).isExactlyInstanceOf(Hit::class.java)
    }

    @Test
    fun `나중에 뽑은 카드들까지의 합이 21초과면, Bust 이다`() {
        val hit = Hit(
            Hands.EMPTY
                .draw(Card(Suit.CLUB, Denomination.TEN))
                .draw(Card(Suit.CLUB, Denomination.JACK))
        )
        val gamePlayer = GamePlayer(Name("김우재"), hit)
            .draw(Deck.initialize { it }) { listOf(Card(Suit.CLUB, Denomination.TWO)) }

        assertThat(gamePlayer.playerState).isExactlyInstanceOf(Bust::class.java)
    }

    @Test
    fun `딜러가 버스트인 경우, 버스트여도 무조건 승리한다`() {
        val hit = Hit(
            Hands.EMPTY
                .draw(Card(Suit.CLUB, Denomination.TEN))
                .draw(Card(Suit.CLUB, Denomination.JACK))
        )
        val gamePlayer = GamePlayer(Name("김우재"), hit)
            .draw(Deck.initialize { it }) { listOf(Card(Suit.CLUB, Denomination.TWO)) }
        val dealer = Dealer(playerState = CLUB_MINIMUM_BUST)

        assertThat(gamePlayer.match(dealer)).isEqualTo(MatchResult.WIN)
    }
}
