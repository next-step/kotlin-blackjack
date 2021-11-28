package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.player.name.Name
import blackjack.domain.player.state.BlackJack
import blackjack.domain.player.state.Bust
import blackjack.domain.player.state.Hit
import blackjack.domain.player.state.Ready
import blackjack.domain.player.state.hands.Hands
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("게임 참가자(GamePlayer)")
internal class GamerTest {

    @Test
    fun `이름과 상태로 생성가능하다`() {
        val gamePlayer = Gamer(Name("김우재"))

        assertAll(
            { assertThat(gamePlayer).isNotNull },
            { assertThat(gamePlayer).isExactlyInstanceOf(Gamer::class.java) }
        )
    }

    @Test
    fun `카드 전략에 따라 드로우를 할 수 있다`() {
        val extraCard = Deck.initialize { it }.pop()
        val expected = Gamer(Name("김우재"), Ready().draw(extraCard))

        val dealer = Gamer(Name("김우재")).draw(Deck.initialize { it }) { listOf(it.pop()) }

        assertThat(dealer).isEqualTo(expected)
    }

    @Test
    fun `처음 뽑은 카드들이 21이면, BlackJack이다`() {
        val gamePlayer = Gamer(Name("김우재")).draw(Deck.initialize { it }) {
            listOf(Card(Suit.CLUB, Denomination.ACE), Card(Suit.CLUB, Denomination.JACK))
        }

        assertThat(gamePlayer.state).isExactlyInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `처음 뽑은 카드들이 21미만이면, Hit이다`() {
        val gamePlayer = Gamer(Name("김우재")).draw(Deck.initialize { it }) {
            listOf(Card(Suit.CLUB, Denomination.ACE), Card(Suit.CLUB, Denomination.TWO))
        }

        assertThat(gamePlayer.state).isExactlyInstanceOf(Hit::class.java)
    }

    @Test
    fun `나중에 뽑은 카드들까지의 합이 21이하면, Hit 이다`() {
        val maximumHit = Hit(
            Hands.EMPTY
                .draw(Card(Suit.CLUB, Denomination.ACE))
                .draw(Card(Suit.CLUB, Denomination.TWO))
        )
        val gamePlayer = Gamer(Name("김우재"), maximumHit)
            .draw(Deck.initialize { it }) { listOf(Card(Suit.CLUB, Denomination.EIGHT)) }

        assertThat(gamePlayer.state).isExactlyInstanceOf(Hit::class.java)
    }

    @Test
    fun `나중에 뽑은 카드들까지의 합이 21초과면, Bust 이다`() {
        val hit = Hit(
            Hands.EMPTY
                .draw(Card(Suit.CLUB, Denomination.TEN))
                .draw(Card(Suit.CLUB, Denomination.JACK))
        )
        val gamePlayer = Gamer(Name("김우재"), hit)
            .draw(Deck.initialize { it }) { listOf(Card(Suit.CLUB, Denomination.TWO)) }

        assertThat(gamePlayer.state).isExactlyInstanceOf(Bust::class.java)
    }
}
