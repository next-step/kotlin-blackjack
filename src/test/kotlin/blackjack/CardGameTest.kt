package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.CardGame
import blackjack.domain.Player
import blackjack.domain.Players
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CardGameTest : DescribeSpec({

    describe("distribute") {
        context("게임에 참여한 모든 플레이어는") {
            val playerA = Player("A")
            val playerB = Player("B")

            val cardDeck = CardDeck.new()
            CardGame(Players(listOf(playerA, playerB)), cardDeck).distribute(1)

            it("카드를 1 장씩 받는다.") {
                playerA.cardCount() shouldBe 1
                playerB.cardCount() shouldBe 1
            }

            it("카드의 수 플레이어 수 만큼 줄어든다.") {
                cardDeck.count() shouldBe 50
            }
        }
    }
})
