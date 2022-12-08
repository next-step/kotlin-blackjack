package blackjack.domain

import blackjack.domain.Number.ACE
import blackjack.domain.Number.EIGHT
import blackjack.domain.Number.FIVE
import blackjack.domain.Number.FOUR
import blackjack.domain.Number.JACK
import blackjack.domain.Number.NINE
import blackjack.domain.Number.QUEEN
import blackjack.domain.Number.SEVEN
import blackjack.domain.Number.THREE
import blackjack.domain.Number.TWO
import blackjack.domain.Sharp.CLOVER
import blackjack.domain.Sharp.DIAMOND
import blackjack.domain.Sharp.HEART
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultTest {

    @Test
    internal fun `딜러가 21을 초과하면 남아있는 플레이어들은 모두 승리한다`() {
        // given
        val dealer = Dealer(Cards(Card(FIVE, HEART), Card(NINE, CLOVER), Card(EIGHT, DIAMOND)))
        val players = Players(
            listOf(
                Player("koi", Cards(Card(TWO, DIAMOND), Card(THREE, HEART))),
                Player("kim", Cards(Card(QUEEN, DIAMOND), Card(JACK, HEART), Card(FOUR, CLOVER)))
            )
        )
        // when
        val result = Result.init(dealer, players)
        // then
        assertThat(result.winner).hasSize(2)
        assertThat(result.loser).isEmpty()
    }

    @Test
    internal fun `딜러가 21을 초과하지 않고 플레이어도 21을 초과하지 않으면 딜러보다 블랙잭 번호와 가까운 플레이어가 승리한다 (동점이면, 딜러가 이긴다)`() {
        // given
        val dealer = Dealer(Cards(Card(EIGHT, HEART), Card(NINE, CLOVER)))
        val players = Players(
            listOf(
                Player("winner1", Cards(Card(QUEEN, DIAMOND), Card(NINE, HEART))),
                Player("winner2", Cards(Card(ACE, DIAMOND), Card(QUEEN, HEART))),
                Player("winner3", Cards(Card(EIGHT, DIAMOND), Card(NINE, HEART))),
                Player("loser1", Cards(Card(ACE, DIAMOND), Card(NINE, HEART), Card(EIGHT, CLOVER))),
                Player("loser2", Cards(Card(SEVEN, DIAMOND), Card(EIGHT, HEART)))
            )
        )
        // when
        val result = Result.init(dealer, players)
        // then
        assertThat(result.winner).hasSize(3)
        assertThat(result.loser).hasSize(2)
    }
}
