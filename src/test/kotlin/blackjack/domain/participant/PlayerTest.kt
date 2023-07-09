package blackjack.domain.participant

import blackjack.domain.card.CardNumber
import blackjack.test.TestObjectGenerator
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "게임이 시작하면 두 장의 카드를 받는다." {
        val player = Player(name = "p1", betAmount = BetAmount(0))
        player.start { TestObjectGenerator.card() }

        player.cards().size shouldBe 2
    }

    "isHit가 false이면 카드를 받지 않는다." {
        val player = Player(name = "p1", betAmount = BetAmount(0))
        player.start { TestObjectGenerator.card() }

        player.play(isHit = { false }, drawCard = { TestObjectGenerator.card() })

        player.cards().size shouldBe 2
    }

    "가지고 있는 카드의 점수가 21이하면 카드를 뽑을 수 있다.." {
        val hand = TestObjectGenerator.handOf21()
        val player = TestObjectGenerator.player(hand = hand)

        player.play(isHit = { true }, drawCard = { TestObjectGenerator.card(CardNumber.THREE) })

        player.cards().size shouldBe 4
    }
})
