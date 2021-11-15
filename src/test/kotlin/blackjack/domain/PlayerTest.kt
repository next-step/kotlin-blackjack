package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @Test
    fun `플레이어는 이름을 가진다`() {
        val player = Player(name = "che1")

        assertThat(player.name).isEqualTo("che1")
    }

    @Test
    fun `플레이어의 덱에 카드를 추가할 수 있다`() {
        val player = Player(name = "che1")

        player.takeCards(Card(CardSymbol.CLOVER, CardNumber(1)))

        assertThat(player.cards).isEqualTo(listOf(Card(CardSymbol.CLOVER, CardNumber(1))))
    }

    @Test
    fun `플레이어가 가진 카드들의 점수를 확인할 수 있다`() {
        val player = Player(name = "che1")

        player.takeCards(
            Card(CardSymbol.CLOVER, CardNumber(2)),
            Card(CardSymbol.CLOVER, CardNumber(3)),
            Card(CardSymbol.CLOVER, CardNumber(4))
        )

        assertThat(player.getTotalScore()).isEqualTo(9)
    }
}
