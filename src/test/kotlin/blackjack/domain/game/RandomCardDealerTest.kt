package blackjack.domain.game

import blackjack.domain.card.Card
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class RandomCardDealerTest {
    @Test
    fun `딜러는 플레이어에게 카드를 지급할 수 있다`() {
        val cardDealer = RandomCardDealer()
        cardDealer.selectCard().shouldBeInstanceOf<Card>()
    }

    @Test
    fun `딜러는 52번 초과하여 카드를 지급할 경우 예외가 발생한다`() {
        val cardDealer = RandomCardDealer()
        (1..Card.TOTAL_COUNT).forEach { _ -> cardDealer.selectCard() }
        shouldThrow<IllegalArgumentException> { cardDealer.selectCard() }
    }
}
