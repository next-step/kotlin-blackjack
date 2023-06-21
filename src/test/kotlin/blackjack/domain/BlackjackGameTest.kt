package blackjack.domain

import blackjack.BlackjackGame
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BlackjackGameTest : BehaviorSpec({

    given("유저가 주어졌다") {
        val users = Users(listOf(User("홍길동")))
        `when`("해당 유저로 게임을 시작하면") {
            then("유저에게 초기덱이 주어진다") {
                val game = BlackjackGame(users)
                game.users.first().deck.size shouldBe BlackjackGame.INITIAL_DECK_SIZE
            }
        }
    }
})
