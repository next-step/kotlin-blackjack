package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.User
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BlackjackGameTest : BehaviorSpec({

    Given("게임이 하나 주어졌다") {
        val game = BlackjackGame()

        When("게임 시작시 시작패를 뽑으면") {
            Then("2개씩 뽑아진다") {
                game.getInitDeck().size shouldBe 2
            }
        }

        When("해당 게임에서 유저에게 카드를 한장 더 줄때") {
            val user = User("홍길동", game.getInitDeck())
            game.addCardTo(user)

            Then("유저의 카드 개수가 증가한다") {
                user.deck.size shouldBe 3
            }
        }
    }
})
