package blackjack.domain

import blackjack.domain.exceptions.ScoreOverflowException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EnumSource

class PlayerTest {
    @Test
    fun `플레이어는 이름을 가진다`() {
        val player = Player(name = "che1")

        assertThat(player.name).isEqualTo("che1")
    }

    @Test
    fun `플레이어는 카드를 받을 수 있다`() {
        val player = Player(name = "che1")

        player.takeCards(listOf(Card(CardSymbol.CLOVER, CardNumber.ACE)))

        assertThat(player.cards).isEqualTo(listOf(Card(CardSymbol.CLOVER, CardNumber.ACE)))
    }

    @Test
    fun `최대 점수를 넘어서면 ScoreOverflowException 을 일으킨다`() {
        val player = Player(name = "che1")
        player.takeCards(
            listOf(
                Card(CardSymbol.CLOVER, CardNumber.JACK),
                Card(CardSymbol.CLOVER, CardNumber.QUEEN),
                Card(CardSymbol.CLOVER, CardNumber.ACE)
            )
        )

        assertThrows<ScoreOverflowException> {
            player.takeCards(
                listOf(
                    Card(CardSymbol.CLOVER, CardNumber.ACE)
                )
            )
        }
    }

    @Test
    fun `플레이어가 가진 카드들의 점수를 확인할 수 있다`() {
        val player = Player(name = "che1")

        player.takeCards(
            listOf(
                Card(CardSymbol.CLOVER, CardNumber.TWO),
                Card(CardSymbol.CLOVER, CardNumber.THREE),
                Card(CardSymbol.CLOVER, CardNumber.FOUR)
            )
        )

        assertThat(player.getTotalScore()).isEqualTo(9)
    }

    @ParameterizedTest
    @EnumSource(value = CardNumber::class, names = ["JACK", "QUEEN", "KING"])
    fun `점수 계산 시 코트 카드는 10점으로 계산한다`(cardNumber: CardNumber) {
        val player = Player("test1")
        player.takeCards(listOf(Card(CardSymbol.CLOVER, cardNumber)))

        assertThat(player.getTotalScore()).isEqualTo(10)
    }

    @ParameterizedTest
    @CsvSource("1,10|21", "1,10,10|21", "1|11", "1,1|12", delimiter = '|')
    fun `점수 계산 시 에이스 카드는 1점 또는 11점으로 계산한다`(cardNumbers: String, expectedScore: Int) {
        val player = Player("test1")
        player.takeCards(
            cardNumbers
                .split(",")
                .map { Card(CardSymbol.CLOVER, CardNumber.fromValue(it.toInt())) }
        )

        assertThat(player.getTotalScore()).isEqualTo(expectedScore)
    }

    @Test
    fun `21점을 넘으면 버스트 상태가 된다`() {
        val player = Player("player")
        player.takeCards(
            listOf(
                Card(CardSymbol.CLOVER, CardNumber.KING),
                Card(CardSymbol.CLOVER, CardNumber.QUEEN),
                Card(CardSymbol.CLOVER, CardNumber.TWO)
            )
        )

        assertThat(player.isBusted).isEqualTo(true)
    }

    @Test
    fun `상대 플레이어의 점수보다 높으면 승리한다`() {
        val player1 = Player("player1")
        player1.takeCards(
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

        assertThat(player1 wins player2).isEqualTo(true)
    }

    @Test
    fun `상대 플레이어의 점수와 같으면 둘 다 승리한다`() {
        val player1 = Player("player1")
        player1.takeCards(
            listOf(
                Card(CardSymbol.CLOVER, CardNumber.KING)
            )
        )

        val player2 = Player("player2")
        player2.takeCards(
            listOf(
                Card(CardSymbol.CLOVER, CardNumber.QUEEN)
            )
        )

        assertThat(player1 wins player2).isEqualTo(true)
        assertThat(player2 wins player1).isEqualTo(true)
    }

    @Test
    fun `상대 플레이어의 점수보다 낮으면 패한다`() {
        val player1 = Player("player1")
        player1.takeCards(
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

        assertThat(player1 wins player2).isEqualTo(false)
    }

    @Test
    fun `버스트인 경우 패한다`() {
        val player1 = Player("player1")
        player1.takeCards(
            listOf(
                Card(CardSymbol.SPADE, CardNumber.KING),
                Card(CardSymbol.SPADE, CardNumber.QUEEN),
                Card(CardSymbol.SPADE, CardNumber.TWO),
            )
        )

        val player2 = Player("player2")
        player2.takeCards(
            listOf(
                Card(CardSymbol.CLOVER, CardNumber.KING)
            )
        )

        assertThat(player1 wins player2).isEqualTo(false)
    }

    @Test
    fun `상대 플레이어가 버스트인 경우 승리한다`() {
        val player1 = Player("player1")
        player1.takeCards(
            listOf(
                Card(CardSymbol.SPADE, CardNumber.KING),
            )
        )

        val player2 = Player("player2")
        player2.takeCards(
            listOf(
                Card(CardSymbol.CLOVER, CardNumber.KING),
                Card(CardSymbol.CLOVER, CardNumber.QUEEN),
                Card(CardSymbol.CLOVER, CardNumber.TWO),
            )
        )

        assertThat(player1 wins player2).isEqualTo(true)
    }
}
