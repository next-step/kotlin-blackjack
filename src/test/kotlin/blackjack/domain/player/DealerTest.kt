package blackjack.domain.player

import blackjack.domain.card.Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

fun Dealer(cards: List<Card>): Dealer {
    val dealer = Dealer()

    cards.forEach {
        dealer.addCardToHand(it)
    }

    return dealer
}

class DealerTest {
    @Test
    fun `딜러의 이름은 "딜러"이다`() {
        assertThat(Dealer().name).isEqualTo("딜러")
    }

    @Test
    fun `딜러일 경우 isFirstCardHidden 은 true 이다`() {
        assertThat(Dealer().isFirstCardHidden).isTrue
    }
}
