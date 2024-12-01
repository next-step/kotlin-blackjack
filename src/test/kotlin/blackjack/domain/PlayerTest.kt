package blackjack.domain

import blackjack.fixture.cardsFixture
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "플레이어는 이름을 가진다." {
        val actual = Player("pobi")

        actual.name shouldBe "pobi"
    }

    "초기의 플레이어의 핸드는 비어있어야 한다" {
        val actual = Player("pobi")

        actual.hands shouldBe 0
    }

    "플레이어는 덱에서 카드를 한 장 뽑을 수 있다." {
        val deck = Deck()
        val actual = Player("pobi")

        actual.hit(deck)

        actual.hands shouldBe 1
    }

    "플레이어는 히트 시 21점을 넘기면 버스트 상태가 된다." {
        val deck = Deck()
        val cards =
            cardsFixture(
                listOf(
                    Card(Suit.SPADE, Rank.TEN),
                    Card(Suit.SPADE, Rank.TEN),
                    Card(Suit.SPADE, Rank.TWO),
                ),
            )
        val actual = Player("pobi", cards)

        actual.hit(deck)

        actual.status shouldBe Player.Status.BURST
    }

    "플레이어는 스테이 상태가 될 수 있다." {
        val actual = Player("pobi")

        actual.toStay()

        actual.status shouldBe Player.Status.STAY
    }

    "플레이어는 점수를 가진다" {
        val cards =
            cardsFixture(
                listOf(
                    Card(Suit.SPADE, Rank.TWO),
                    Card(Suit.SPADE, Rank.THREE),
                    Card(Suit.SPADE, Rank.TEN),
                ),
            )
        val actual = Player("pobi", cards)

        actual.score shouldBe 15
    }
})
