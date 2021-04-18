package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardShape
import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import blackjack.domain.state.started.Hit
import blackjack.domain.state.started.finished.Stay
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun stay() {
        val cards = Cards(listOf(Card(CardShape.SPADE, CardType.TWO), Card(CardShape.SPADE, CardType.THREE)))
        val player = Player("song", Hit(cards))

        player.stay()
        Assertions.assertThat(player.state).isInstanceOf(Stay::class.java)
    }
}
