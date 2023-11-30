package blackjack.domain.game

import blackjack.domain.card.Card
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class RandomCardDealerTest : FunSpec({
    test("딜러는 플레이어에게 카드를 지급할 수 있다") {
        val cardDealer = RandomCardDealer()
        cardDealer.selectCard().shouldBeInstanceOf<Card>()
    }

    test("딜러는 52번 초과하여 카드를 지급할 경우 예외가 발생한다") {
        val cardDealer = RandomCardDealer()
        (1..52).forEach { _ -> cardDealer.selectCard() }
        shouldThrow<IllegalArgumentException> { cardDealer.selectCard() }
    }

    test("딜러는 일정 횟수만큼 플레이어에게 카드를 지급할 수 있다") {
        val cardDealer = RandomCardDealer()
        val cardSet = cardDealer.selectCard(10)
        cardSet.cards.size shouldBe 10
    }
})
