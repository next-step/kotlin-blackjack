package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardSymbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CustomerTest {

    @Test
    fun `참가자는 이름이 있다`() {
        val player = Customer("정주영")
        assertThat(player.name).isEqualTo("정주영")
    }

    @Test
    fun `참가자의 카드`() {
        val player = Customer("정주영")
        player.acceptCard(Card.of(CardSymbol.CLOVER, CardNumber.TWO))
        player.acceptCard(Card.of(CardSymbol.DIAMONDS, CardNumber.TWO))

        assertThat(player.cards).containsExactlyElementsOf(
            listOf(
                Card.of(CardSymbol.CLOVER, CardNumber.TWO),
                Card.of(CardSymbol.DIAMONDS, CardNumber.TWO)
            )
        )
    }

    @Test
    fun `hit 가능 여부`() {
        val player = Customer("정주영")
        // 2
        player.acceptCard(Card.of(CardSymbol.CLOVER, CardNumber.TWO))
        assertThat(player.canHit()).isEqualTo(true)

        player.acceptCard(Card.of(CardSymbol.CLOVER, CardNumber.TEN))
        assertThat(player.canHit()).isEqualTo(true)

        player.acceptCard(Card.of(CardSymbol.CLOVER, CardNumber.NINE))
        assertThat(player.canHit()).isEqualTo(false)
    }
}
