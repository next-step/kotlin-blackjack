package blackjack.domain.game

import blackjack.domain.card.Card
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class RandomCardDealerTest {
    @Test
    fun `딜러는 플레이어에게 카드를 지급할 수 있다`() {
        val cardDealer = RandomCardDealer()
        cardDealer.selectCard().shouldBeInstanceOf<Card>()
    }
}
