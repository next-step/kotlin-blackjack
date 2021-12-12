package blackjack.strategy.draw

import blackjack.domain.card.CardDeck
import blackjack.domain.strategy.draw.HitDrawStrategy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HitDrawStrategyTest {

    @Test
    fun `HitDrawStrategy는 CardDeck으로부터 카드한장을 가진 List를 받는다`() {
        val cardDeck = CardDeck()
        val hitDrawStrategy = HitDrawStrategy

        val cardList = hitDrawStrategy.draw(cardDeck)

        assertThat(cardList.size).isEqualTo(1)
    }
}
