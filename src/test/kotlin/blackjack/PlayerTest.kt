package blackjack

import blackjack.domain.Player
import blackjack.domain.enums.CardPoint
import blackjack.domain.enums.CardShape
import blackjack.factory.FixedCardFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PlayerTest {
    @Test
    fun `플레이어는 카드를 소지할 수 있다`() {
        val player = Player("서정국")
        player.takeCard(FixedCardFactory(CardShape.HEART, CardPoint.KING).create())
        val card = player.cards[0]

        assertThat(Pair(card.shape, card.point)).isEqualTo(Pair(CardShape.HEART, CardPoint.KING))
    }

    @ParameterizedTest
    @CsvSource("y, true", "n, false")
    fun `플레이어는 추가 카드 여부를 결정할 수 있다`(input: String, expect: Boolean) {
        val player = Player("서정국")
        assertThat(player.needCard(input)).isEqualTo(expect)
    }
}
