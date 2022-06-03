package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.Player
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PlayerTest : DescribeSpec({

    describe("draw") {
        context("카드 덱이 비어있지 않다면") {
            it("호출 횟수 만큼 카드를 가져간다.") {
                val player = Player("name")
                val cardDeck = CardDeck.new()
                player.draw(cardDeck)
                player.draw(cardDeck)
                player.cardCount() shouldBe 2
            }
        }
    }
})
