package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.PlayerAction
import blackjack.mock.InputProcessorMock
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class InGameTest : DescribeSpec({

    describe("게임 진행") {
        context("플레이어가 한 장의 카드를 더 받겠다고 한 경우") {
            val game = BlackJackGame(InputProcessorMock(playerAction = PlayerAction.HIT))
            val deckCount = game.dealer.deck.cards.size
            val handCount = game.players.playerInTurn.hand.cards.size

            context("게임 진행 스테이지를 진행시키면") {
                val stage = InGame(game)
                stage.progress()

                it("플레이어의 카드는 증가") {
                    game.players.playerInTurn.hand.cards.size shouldBe handCount + 1
                }

                it("덱에서 카드는 제거") {
                    game.dealer.deck.cards.size shouldBe deckCount - 1
                }
            }
        }
    }

})
