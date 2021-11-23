package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.player.state.BlackJack
import blackjack.domain.player.state.BlackJackTest.Companion.TEST_BLACKJACK
import blackjack.domain.player.state.BlackJackTest.Companion.blackJackCards
import blackjack.domain.player.state.Bust
import blackjack.domain.player.state.Hit
import blackjack.domain.player.state.HitTest
import blackjack.domain.player.state.HitTest.Companion.TEST_MAXIMUM_HIT
import blackjack.domain.player.state.HitTest.Companion.hitCards
import blackjack.domain.player.state.HitTest.Companion.maximumHitCards
import blackjack.domain.player.state.Ready
import blackjack.domain.player.state.Stay
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
        val expected = GamePlayer(Name("김우재"), TEST_BLACKJACK)

        val gamePlayer = GamePlayer(Name("김우재")).draw(Deck.initialize { it }) { blackJackCards() }

        assertAll(
            { AssertionsForClassTypes.assertThat(gamePlayer.playerState is BlackJack).isTrue },
            { AssertionsForClassTypes.assertThat(gamePlayer).isEqualTo(expected) },
        )
    }

    @Test
    fun `처음 뽑은 카드들이 21미만이면, Hit이다`() {
        val expected = GamePlayer(Name("김우재"), TEST_MAXIMUM_HIT)

        val gamePlayer = GamePlayer(Name("김우재")).draw(Deck.initialize { it }) { maximumHitCards() }

        assertAll(
            { AssertionsForClassTypes.assertThat(gamePlayer.playerState is Hit).isTrue },
            { AssertionsForClassTypes.assertThat(gamePlayer).isEqualTo(expected) },
        )
    }

    @Test
    fun `나중에 뽑은 카드들까지의 합이 21이하면, Hit 이다`() {
        val expectedStayCards = maximumHitCards() + Card(Suit.CLUB, Denomination.ACE)
        val expected = GamePlayer(Name("김우재"), Hit(Hands.from(expectedStayCards)))

        val gamePlayer = GamePlayer(Name("김우재")).draw(Deck.initialize { it }) { expectedStayCards }

        assertAll(
            { AssertionsForClassTypes.assertThat(gamePlayer.playerState is Hit).isTrue },
            { AssertionsForClassTypes.assertThat(gamePlayer).isEqualTo(expected) },
        )
    }

    @Test
    fun `나중에 뽑은 카드들까지의 합이 21초과면, Bust 이다`() {
        val expectedStayCards = maximumHitCards() + Card(Suit.CLUB, Denomination.TWO)
        val expected = GamePlayer(Name("김우재"), Bust(Hands.from(expectedStayCards)))

        val gamePlayer = GamePlayer(Name("김우재")).draw(Deck.initialize { it }) { expectedStayCards }

        assertAll(
            { AssertionsForClassTypes.assertThat(gamePlayer.playerState is Bust).isTrue },
            { AssertionsForClassTypes.assertThat(gamePlayer).isEqualTo(expected) },
        )
    }
}
