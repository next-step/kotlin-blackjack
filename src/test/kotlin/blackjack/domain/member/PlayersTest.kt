package blackjack.domain.member

import blackjack.domain.Bet
import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.GameState.LOSE
import blackjack.domain.GameState.WIN
import blackjack.domain.GameState.WIN_BLACKJACK
import blackjack.domain.Number.ACE
import blackjack.domain.Number.EIGHT
import blackjack.domain.Number.FIVE
import blackjack.domain.Number.FOUR
import blackjack.domain.Number.JACK
import blackjack.domain.Number.NINE
import blackjack.domain.Number.QUEEN
import blackjack.domain.Number.SIX
import blackjack.domain.Number.THREE
import blackjack.domain.Number.TWO
import blackjack.domain.Sharp.CLOVER
import blackjack.domain.Sharp.DIAMOND
import blackjack.domain.Sharp.HEART
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

internal class PlayersTest {
    @Test
    internal fun `참가자가 두명이 아니면 예외가 발생한다`() {
        // given
        // when, then
        assertThatIllegalArgumentException().isThrownBy { Players(emptyList()) }
        assertThatIllegalArgumentException().isThrownBy {
            Players(
                listOf(Player("koi", Cards(Card(TWO, DIAMOND), Card(THREE, HEART))))
            )
        }
    }

    @Test
    internal fun `딜러의 점수가 21점이 넘으면 블랙잭 사용자는 블랙잭 승리를 그 외 사용자는 모두 승리한다`() {
        // given
        val dealer = Dealer(Cards(Card(ACE, HEART), Card(QUEEN, CLOVER), Card(TWO, DIAMOND)))
        val players = Players(
            listOf(
                Player("blackjack", Cards(Card(ACE, HEART), Card(JACK, CLOVER)), Bet.of(1000)),
                Player("winner1", Cards(Card(NINE, HEART), Card(EIGHT, CLOVER)), Bet.of(1000)),
                Player("winner2", Cards(Card(ACE, HEART), Card(EIGHT, CLOVER), Card(FIVE, HEART)), Bet.of(1000))
            )
        )

        // when
        val gameResultPlayers = players.toResultPlayers(dealer)

        // then
        assertThat(gameResultPlayers.items)
            .extracting("gameState").containsExactly(WIN_BLACKJACK, WIN, WIN)
    }

    @Test
    internal fun `딜러의 점수가 블랙잭이면 블랙잭인 사용자는 승리 외 다른 사용자는 패배한다`() {
        // given
        val dealer = Dealer(Cards(Card(ACE, HEART), Card(QUEEN, CLOVER)))
        val players = Players(
            listOf(
                Player("blackjack", Cards(Card(ACE, HEART), Card(JACK, CLOVER)), Bet.of(1000)),
                Player("player1", Cards(Card(NINE, HEART), Card(EIGHT, CLOVER)), Bet.of(1000)),
                Player("player2", Cards(Card(ACE, HEART), Card(EIGHT, CLOVER), Card(FIVE, HEART)), Bet.of(1000))
            )
        )

        // when
        val gameResultPlayers = players.toResultPlayers(dealer)

        // then
        assertThat(gameResultPlayers.items)
            .extracting("gameState").containsExactly(WIN, LOSE, LOSE)
    }

    @Test
    internal fun `딜러의 점수가 21 미만이면 블랙잭 사용자는 블랙잭 승리를, 그 외는 블랙잭과 가까운 사용자가 승리한다`() {
        // given
        val dealer = Dealer(Cards(Card(ACE, HEART), Card(SIX, CLOVER)))
        val players = Players(
            listOf(
                Player("blackjack", Cards(Card(ACE, HEART), Card(JACK, CLOVER)), Bet.of(1000)),
                Player("winner", Cards(Card(JACK, HEART), Card(EIGHT, CLOVER)), Bet.of(1000)),
                Player("loser", Cards(Card(TWO, HEART), Card(THREE, CLOVER), Card(FIVE, HEART)), Bet.of(1000)),
                Player("loser2", Cards(Card(FOUR, HEART), Card(FIVE, CLOVER)), Bet.of(1000))
            )
        )

        // when
        val gameResultPlayers = players.toResultPlayers(dealer)

        // then
        assertThat(gameResultPlayers.items).extracting("gameState").containsExactly(WIN_BLACKJACK, WIN, LOSE, LOSE)
    }
}
