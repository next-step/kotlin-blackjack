package blackjack.domain.participant

import blackjack.domain.card.CardNumber
import blackjack.test.FakeGenerator
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "게임이 시작하면 두 장의 카드를 받는다." {
        val player = Player("p1")
        player.start { FakeGenerator.card() }

        player.cards().size shouldBe 2
    }

    "isHit가 false이면 카드를 받지 않는다." {
        val player = Player("p1")
        player.start { FakeGenerator.card() }

        player.play(isHit = { false }, drawCard = { FakeGenerator.card() })

        player.cards().size shouldBe 2
    }

    "가지고 있는 카드의 점수가 21이하면 카드를 뽑을 수 있다.." {
        val hand = FakeGenerator.playerHandOf21()
        val player = Player("p1", hand)

        player.play(isHit = { true }, drawCard = { FakeGenerator.card(CardNumber.THREE) })

        player.cards().size shouldBe 4
    }
})
