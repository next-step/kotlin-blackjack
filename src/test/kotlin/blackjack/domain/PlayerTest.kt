package blackjack.domain

import blackjack.fixture.handsFixture
import blackjack.fixture.playerFixture
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "플레이어는 이름을 가진다." {
        val actual = Player("pobi")

        actual.name shouldBe "pobi"
    }

    "초기의 플레이어의 핸드는 비어있어야 한다" {
        val actual = Player("pobi")

        actual.handSize shouldBe 0
    }

    "플레이어는 히트 시 핸드에 카드를 한 장 추가한다." {
        val deck = Deck()
        val actual = Player("pobi")

        actual.hit(deck)

        actual.handSize shouldBe 1
    }

    "플레이어는 히트 시 21점을 넘기면 버스트 상태가 된다." {
        val deck = Deck()
        val hands =
            handsFixture(
                Card(Suit.SPADE, Rank.TEN),
                Card(Suit.SPADE, Rank.TEN),
                Card(Suit.SPADE, Rank.TWO),
            )
        val actual = playerFixture(hands = hands)

        actual.hit(deck)

        actual.score shouldBeGreaterThan 21
        actual.isBurst shouldBe true
    }

    "플레이어는 점수를 가진다" {
        val hands =
            handsFixture(
                Card(Suit.SPADE, Rank.TWO),
                Card(Suit.SPADE, Rank.THREE),
                Card(Suit.SPADE, Rank.TEN),
            )
        val actual = playerFixture(hands = hands)

        actual.score shouldBe 15
    }
})
