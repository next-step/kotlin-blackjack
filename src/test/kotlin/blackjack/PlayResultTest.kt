package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayResultTest {
    private val pobi = CardPlayer.Player("pobi")
    private val jason = CardPlayer.Player("jason")
    private val dealer = CardPlayer.Dealer()

    @Test
    fun `승패를 계산한다`() {
        pobi.apply {
            accept(Card("K"))
            accept(Card("K"))
        }.also {
            assertThat(it.score()).isEqualTo(20)
        }

        jason.apply {
            accept(Card("K"))
            accept(Card("2"))
        }.also {
            assertThat(it.score()).isEqualTo(12)
        }

        dealer.apply {
            accept(Card("K"))
            accept(Card("9"))
        }.also {
            assertThat(it.score()).isEqualTo(19)
        }

        val result = Players(dealer, pobi, jason).gameResult()

        assertThat(result).contains(
            PlayerResult(dealer, wins = 1, losses = 1),
            PlayerResult(pobi, wins = 1),
            PlayerResult(jason, losses = 1)
        )
    }

    @Test
    fun `딜러가 21을 초과하면 플레이어가 승리한다`() {
        dealer.apply {
            accept(Card("K"))
            accept(Card("K"))
            accept(Card("K"))
        }.also {
            assertThat(it.score()).isEqualTo(30)
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
        pobi.apply {
            accept(Card("K"))
            accept(Card("K"))
            accept(Card("2"))
        }.also {
            assertThat(it.busts()).isTrue()
        }
        jason.apply {
            accept(Card("K"))
            accept(Card("K"))
        }.also {
            assertThat(it.busts()).isFalse()
        }
        dealer.apply {
            accept(Card("K"))
            accept(Card("K"))
            accept(Card("2"))
        }.also {
            assertThat(it.busts()).isTrue()
        }

        val result = Players(dealer, pobi, jason).gameResult()

        assertThat(result).contains(
            PlayerResult(dealer, wins = 1, losses = 1),
            PlayerResult(pobi, losses = 1),
            PlayerResult(jason, wins = 1)
        )
    }

    fun Card(name: String) = Card(name, Symbol.values().random())
}
