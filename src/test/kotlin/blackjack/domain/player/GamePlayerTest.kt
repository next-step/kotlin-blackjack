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
import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("게임 참가자(GamePlayer)")
internal class GamePlayerTest {

    @Test
    fun `이름과 상태로 생성가능하다`() {
        val gamePlayer = GamePlayer(Name("김우재"))

        assertAll(
            { AssertionsForClassTypes.assertThat(gamePlayer).isNotNull },
            { AssertionsForClassTypes.assertThat(gamePlayer).isExactlyInstanceOf(GamePlayer::class.java) }
        )
    }

    @Test
    fun `카드 전략에 따라 드로우를 할 수 있다`() {
        val extraCard = Deck.initialize { it }.pop()
        val expected = GamePlayer(Name("김우재"), Ready().draw(extraCard))

        val dealer = GamePlayer(Name("김우재")).draw(Deck.initialize { it }) { listOf(it.pop()) }

        AssertionsForClassTypes.assertThat(dealer).isEqualTo(expected)
    }

    @Test
    fun `처음 뽑은 카드들이 21이면, BlackJack이다`() {
        val blackJack = BlackJack(Hands.EMPTY
            .draw(Card(Suit.CLUB, Denomination.ACE))
            .draw(Card(Suit.CLUB, Denomination.JACK))
        )
        val gamePlayer = GamePlayer(Name("김우재"), blackJack)

        AssertionsForClassTypes.assertThat(gamePlayer.playerState is BlackJack).isTrue
    }

    @Test
    fun `처음 뽑은 카드들이 21미만이면, Hit이다`() {
        val maximumHit = Hit(Hands.EMPTY
            .draw(Card(Suit.CLUB, Denomination.ACE))
            .draw(Card(Suit.CLUB, Denomination.TWO))
        )
        val gamePlayer = GamePlayer(Name("김우재"), maximumHit)

        AssertionsForClassTypes.assertThat(gamePlayer.playerState is Hit).isTrue
    }

    @Test
    fun `나중에 뽑은 카드들까지의 합이 21이하면, Hit 이다`() {
        val maximumHit = Hit(Hands.EMPTY
            .draw(Card(Suit.CLUB, Denomination.ACE))
            .draw(Card(Suit.CLUB, Denomination.TWO))
        )
        val gamePlayer = GamePlayer(Name("김우재"), maximumHit
            .draw(Card(Suit.CLUB, Denomination.EIGHT)))

        AssertionsForClassTypes.assertThat(gamePlayer.playerState is Hit).isTrue
    }

    @Test
    fun `나중에 뽑은 카드들까지의 합이 21초과면, Bust 이다`() {
        val maximumHit = Hit(Hands.EMPTY
            .draw(Card(Suit.CLUB, Denomination.TEN))
            .draw(Card(Suit.CLUB, Denomination.JACK))
        )
        val gamePlayer = GamePlayer(Name("김우재"), maximumHit.draw(Card(Suit.CLUB, Denomination.TWO)))

        AssertionsForClassTypes.assertThat(gamePlayer.playerState is Bust).isTrue
    }
}
