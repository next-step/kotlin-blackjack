package blackjack.domain

import blackjack.dealerWith
import blackjack.playerWith
import blackjack.scoreIs
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayResultTest {
    @Test
    fun `승패를 계산한다`() {
        val pobi = playerWith("pobi", "K", "K") {
            it scoreIs 20
        }
        val jason = playerWith("jason", "K", "2") {
            it scoreIs 12
        }
        val dealer = dealerWith("K", "9") {
            it scoreIs 19
        }

        val result = Players(dealer, pobi, jason).gameResult()

        assertThat(result).contains(
            PlayerResult(pobi, PlayResult.WINS),
            PlayerResult(jason, PlayResult.LOSSES)
        )
    }

    @Test
    fun `무승부가 있다`() {
        val pobi = playerWith("pobi", "K", "K") {
            it scoreIs 20
        }
        val jason = playerWith("jason", "K", "K") {
            it scoreIs 20
        }
        val dealer = dealerWith("K", "K") {
            it scoreIs 20
        }

        val result = Players(dealer, pobi, jason).gameResult()

        assertThat(result)
            .containsExactlyInAnyOrder(PlayerResult(pobi, PlayResult.DRAWS), PlayerResult(jason, PlayResult.DRAWS))
    }

    @Test
    fun `딜러가 21을 초과하면 플레이어가 승리한다`() {
        val pobi = playerWith("pobi", "A") {
            it scoreIs 11
        }

        val dealer = dealerWith("K", "K", "K") {
            it scoreIs 30
        }

        val list = Players(dealer, pobi).gameResult()

        assertThat(list)
            .filteredOnAssertions { assertThat(it).isEqualTo(PlayerResult(pobi, PlayResult.WINS)) }
            .hasSize(1)
    }

    @Test
    fun `pobi 가 21을 초과하고 딜러도 초과하면 남은 jason 승, 딜러 1승 1패 pobi 패`() {
        val pobi = playerWith("pobi", "K", "K", "2") {
            it busts true
        }

        val jason = playerWith("json", "K", "K") {
            it busts false
        }

        val dealer = dealerWith("K", "K", "2") {
            it busts true
        }

        val result = Players(dealer, pobi, jason).gameResult()

        assertThat(result).contains(
            PlayerResult(pobi, PlayResult.LOSSES),
            PlayerResult(jason, PlayResult.WINS)
        )
    }

    private infix fun CardPlayer.busts(busted: Boolean) {
        assertThat(busts()).isEqualTo(busted)
    }
}
