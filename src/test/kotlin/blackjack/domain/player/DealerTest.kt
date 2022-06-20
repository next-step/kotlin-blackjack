package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 이름은 "딜러"이다`() {
        assertThat(Dealer(`16 point card`()).name).isEqualTo("딜러")
    }

    @Test
    fun `딜러의 카드가 16 이하일 경우 shouldDrawCard 는 true 이다`() {
        assertThat(Dealer(`16 point card`()).shouldDrawCard).isTrue
    }

    @Test
    fun `딜러의 카드가 17 이상일 경우 shouldDrawCard 는 false 이다`() {
        assertThat(Dealer(`17 point card`()).shouldDrawCard).isFalse
    }
}

private fun `16 point card`() = listOf(Card.Ten(CardSuit.CLOVER), Card.Six(CardSuit.CLOVER))
private fun `17 point card`() = listOf(Card.Ten(CardSuit.CLOVER), Card.Seven(CardSuit.CLOVER))
