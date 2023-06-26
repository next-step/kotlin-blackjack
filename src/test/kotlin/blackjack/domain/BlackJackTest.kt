package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest {
    @Test
    fun checkBurstIsFalse() {
        val player = Player("은지")
        player.addCards(listOf(Card(CardShape.DIAMOND, CardSymbol.FIVE), Card(CardShape.HEART, CardSymbol.EIGHT)))
        assertThat(BlackJack.checkBurst(player)).isEqualTo(false)
    }

    @Test
    fun checkBurstIsTrue() {
        val player = Player("은지")
        player.addCards(
            listOf(
                Card(CardShape.DIAMOND, CardSymbol.QUEEN),
                Card(CardShape.HEART, CardSymbol.KING),
                Card(CardShape.HEART, CardSymbol.EIGHT)
            )
        )
        assertThat(BlackJack.checkBurst(player)).isEqualTo(true)
    }
}
