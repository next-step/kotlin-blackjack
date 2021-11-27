package blackjack.domain

import blackjack.domain.exceptions.ScoreOverflowException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DealerTest {
    @Test
    fun `딜러는 점수가 17 이상이면 더 이상 카드를 받지 못한다`() {
        val dealer = Dealer()
        dealer.takeCards(
            listOf(
                Card(CardSymbol.SPADE, CardNumber.ACE),
                Card(CardSymbol.SPADE, CardNumber.SIX)
            )
        )

        assertThrows<ScoreOverflowException> {
            dealer.takeCards(
                listOf(Card(CardSymbol.SPADE, CardNumber.TWO))
            )
        }
        assertThat(dealer.canTakeCards).isEqualTo(false)
    }

    @Test
    fun `상대 플레이어의 점수보다 높으면 승리한다`() {
        val dealer = Dealer()
        dealer.takeCards(
            listOf(
                Card(CardSymbol.CLOVER, CardNumber.KING)
            )
        )

        val player2 = Player("player2")
        player2.takeCards(
            listOf(
                Card(CardSymbol.CLOVER, CardNumber.NINE)
            )
        )

        assertThat(dealer wins player2).isEqualTo(true)
    }

    @Test
    fun `상대 플레이어의 점수보다 낮으면 패한다`() {
        val dealer = Dealer()
        dealer.takeCards(
            listOf(
                Card(CardSymbol.CLOVER, CardNumber.NINE)
            )
        )

        val player2 = Player("player2")
        player2.takeCards(
            listOf(
                Card(CardSymbol.CLOVER, CardNumber.KING)
            )
        )

        assertThat(dealer wins player2).isEqualTo(false)
    }

    @Test
    fun `상대 플레이어와 동점일 경우 패한다`() {
        val dealer = Dealer()
        dealer.takeCards(
            listOf(
                Card(CardSymbol.SPADE, CardNumber.KING)
            )
        )

        val player = Player("test")
        player.takeCards(
            listOf(
                Card(CardSymbol.CLOVER, CardNumber.KING)
            )
        )

        assertThat(dealer wins player).isEqualTo(false)
    }

    @Test
    fun `버스트인 경우 패한다`() {
        val dealer = Dealer()
        dealer.takeCards(
            listOf(
                Card(CardSymbol.SPADE, CardNumber.KING),
                Card(CardSymbol.SPADE, CardNumber.QUEEN),
                Card(CardSymbol.SPADE, CardNumber.TWO),
            )
        )

        val player = Player("test")
        player.takeCards(
            listOf(
                Card(CardSymbol.CLOVER, CardNumber.KING)
            )
        )

        assertThat(dealer wins player).isEqualTo(false)
    }

    @Test
    fun `상대 플레이어가 버스트인 경우 승리한다`() {
        val dealer = Dealer()
        dealer.takeCards(
            listOf(
                Card(CardSymbol.SPADE, CardNumber.KING),

            )
        )

        val player = Player("test")
        player.takeCards(
            listOf(
                Card(CardSymbol.CLOVER, CardNumber.KING),
                Card(CardSymbol.CLOVER, CardNumber.QUEEN),
                Card(CardSymbol.CLOVER, CardNumber.TWO),
            )
        )

        assertThat(dealer wins player).isEqualTo(true)
    }
}
