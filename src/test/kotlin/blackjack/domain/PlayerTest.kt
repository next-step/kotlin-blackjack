package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
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
    fun `최대 점수를 넘어서면 카드를 받지 못한다`() {
        val player = Player(name = "che1")
        player.takeCards(
            listOf(
                Card(CardSymbol.CLOVER, CardNumber.JACK),
                Card(CardSymbol.CLOVER, CardNumber.QUEEN),
                Card(CardSymbol.CLOVER, CardNumber.ACE)
            )
        )

        player.takeCards(
            listOf(
                Card(CardSymbol.CLOVER, CardNumber.ACE)
            )
        )

        assertThat(player.cards).isEqualTo(
            listOf(
                Card(CardSymbol.CLOVER, CardNumber.JACK),
                Card(CardSymbol.CLOVER, CardNumber.QUEEN),
                Card(CardSymbol.CLOVER, CardNumber.ACE)
            )
        )
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
}
