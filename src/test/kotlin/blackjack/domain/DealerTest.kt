package blackjack.domain

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
import blackjack.domain.Number.SEVEN
import blackjack.domain.Number.SIX
import blackjack.domain.Number.THREE
import blackjack.domain.Number.TWO
import blackjack.domain.Sharp.CLOVER
import blackjack.domain.Sharp.DIAMOND
import blackjack.domain.Sharp.HEART
import blackjack.domain.member.Dealer
import blackjack.domain.member.Player
import blackjack.domain.member.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    internal fun `딜러의 카드 합계가 17점 이하면 카드를 뽑을 수 있다`() {
        // given
        val dealer = Dealer(Cards(Card(QUEEN, HEART), Card(SEVEN, CLOVER)))

        // when, then
        assertThat(dealer.ableMoreDrawCard()).isFalse
    }

    @Test
    internal fun `딜러의 카드 합계가 17점 이상이면 카드를 뽑을 수 없다`() {
        // given
        val dealer = Dealer(Cards(Card(NINE, HEART), Card(SEVEN, CLOVER)))

        // when, then
        assertThat(dealer.ableMoreDrawCard()).isTrue
    }

    @Test
    internal fun `딜러의 카드 합계가 21 이하이면 fasle 를 반환한다`() {
        // given
        val dealer = Dealer(Cards(Card(NINE, HEART), Card(SEVEN, CLOVER)))

        // when, then
        assertThat(dealer.isOverBlackjackNumber()).isFalse
    }

    @Test
    internal fun `딜러의 카드 합계가 21 초과면 fasle 를 반환한다`() {
        // given
        val dealer = Dealer(Cards(Card(ACE, HEART), Card(QUEEN, CLOVER), Card(TWO, DIAMOND)))

        // when, then
        assertThat(dealer.isOverBlackjackNumber()).isTrue
    }

    @Test
    internal fun `딜러의 점수가 21 초과면 딜러가 패배한다`() {
        // given
        val dealer = Dealer(Cards(Card(QUEEN, HEART), Card(QUEEN, CLOVER), Card(TWO, DIAMOND)))
        val player = Player("koi", Cards(Card(TWO, HEART), Card(THREE, CLOVER)))

        // when, then
        assertThat(dealer.isWin(player)).isFalse
    }

    @Test
    internal fun `딜러의 점수가 21 이하면 21과 번호가 가까운 사용자가 승리한다`() {
        // given
        val dealer = Dealer(Cards(Card(QUEEN, HEART), Card(QUEEN, CLOVER)))
        val losePlayer = Player("koi", Cards(Card(TWO, HEART), Card(THREE, CLOVER)))
        val winPlayer = Player("koi2", Cards(Card(ACE, HEART), Card(QUEEN, CLOVER)))

        // when, then
        assertThat(dealer.isWin(losePlayer)).isTrue
        assertThat(dealer.isWin(winPlayer)).isFalse
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
        val gameResultPlayers = dealer.gameResultPlayers(players)

        // then
        assertThat(gameResultPlayers.items).extracting("gameState").containsExactly(WIN_BLACKJACK, WIN, WIN)
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
        val gameResultPlayers = dealer.gameResultPlayers(players)

        // then
        assertThat(gameResultPlayers.items).extracting("gameState").containsExactly(WIN, LOSE, LOSE)
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
        val gameResultPlayers = dealer.gameResultPlayers(players)

        // then
        assertThat(gameResultPlayers.items).extracting("gameState").containsExactly(WIN_BLACKJACK, WIN, LOSE, LOSE)
    }
}
