package domain

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Player
import blackjack.domain.Suit
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PlayerTest : DescribeSpec({
    describe("player init test") {
        it("사용자의 이름을 받아 객체를 생성한다.") {
            val inputUsername = "pobi"
            val actual = Player(inputUsername)
            actual.name shouldBe "pobi"
            actual.ownedCards.size shouldBe 0
        }
    }

    describe("hit card test") {
        lateinit var player: Player
        beforeTest { player = Player("pobi") }

        it("사용자가 소유한 카드리스트에 hit 한 카드를 추가한다.") {
            val card = Card(Suit.SPADES, CardNumber.NINE)
            player.hit(card)

            player.ownedCards.size shouldBe 1
            player.ownedCards[0] shouldBe card
        }
    }
})
