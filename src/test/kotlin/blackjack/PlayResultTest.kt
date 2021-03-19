package blackjack

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
            PlayerResult(dealer, wins = 1, losses = 1),
            PlayerResult(pobi, wins = 1),
            PlayerResult(jason, losses = 1)
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
            .filteredOnAssertions { assertThat(it).isEqualTo(PlayerResult(dealer, draws = 2)) }
            .hasSize(1)

        assertThat(result)
            .contains(PlayerResult(pobi, draws = 1), PlayerResult(jason, draws = 1))
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
            .hasSize(2)
            .filteredOnAssertions { assertThat(it).isEqualTo(PlayerResult(pobi, wins = 1)) }
            .hasSize(1)

        assertThat(list)
            .filteredOnAssertions { assertThat(it).isEqualTo(PlayerResult(dealer, losses = 1)) }
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
            PlayerResult(dealer, wins = 1, losses = 1),
            PlayerResult(pobi, losses = 1),
            PlayerResult(jason, wins = 1)
        )
    }

    private fun playerWith(name: String, vararg cardNames: String, assert: (CardPlayer) -> Unit): CardPlayer {
        val cards = cardNames.map { Card(it, Symbol.values().random()) }.toList()
        return CardPlayer.Player(name, cards)
            .also {
                assert(it)
            }
    }

    private fun dealerWith(vararg cardNames: String, assert: (CardPlayer) -> Unit): CardPlayer.Dealer {
        return CardPlayer.Dealer(playerWith("dealer", *cardNames, assert = assert))
    }

    private infix fun CardPlayer.scoreIs(score: Int) {
        assertThat(score()).isEqualTo(score)
    }

    private infix fun CardPlayer.busts(busted: Boolean) {
        assertThat(busts()).isEqualTo(busted)
    }
}
