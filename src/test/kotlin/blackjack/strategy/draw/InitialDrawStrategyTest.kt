package blackjack.strategy.draw

import blackjack.domain.card.CardDeck
import blackjack.domain.strategy.draw.InitialDrawStrategy
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class InitialDrawStrategyTest {

    @Test
    fun `InitialDrawStrategy는 CardDeck으로부터 카드두장을 가진 List를 받는다`() {
        val cardDeck = CardDeck()
        val initialDrawStrategy = InitialDrawStrategy

        val cardList = initialDrawStrategy.draw(cardDeck)

        Assertions.assertThat(cardList.size).isEqualTo(2)
    }
}
