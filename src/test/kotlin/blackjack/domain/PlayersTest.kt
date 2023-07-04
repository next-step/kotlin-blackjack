package blackjack.domain

import blackjack.test.FakeGenerator
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayersTest : StringSpec({
    "플레이어의 이름이 중복되면 예외를 던진다." {
        shouldThrowAny { Players.of(listOf("p1", "p1")) }
    }

    "각 플레이어는 카드를 받을 수 있다." {
        val p1 = Player("p1")
        val p2 = Player("p2")
        val players = Players(listOf(p1, p2))
        val cards = listOf(FakeGenerator.card(), FakeGenerator.card())
        val result = players.addCards { cards }

        result.values[0].hand.cards shouldBe cards
        result.values[1].hand.cards shouldBe cards
    }

})
