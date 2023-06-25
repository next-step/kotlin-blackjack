package blackjack.controller

import blackjack.domain.Card
import blackjack.domain.JackQueenKingCardNumber
import blackjack.domain.Suit
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import util.FixedCardSelector

class BlackjackGameTest : BehaviorSpec({

    given("유저가 주어졌다") {
        System.setIn("홍길동".byteInputStream())
        `when`("해당 유저로 게임을 시작하면") {
            then("유저에게 초기덱이 주어진다") {
                val game = BlackjackGame()
                game.users.first().deck.size shouldBe BlackjackGame.INITIAL_DECK_SIZE
            }
        }
    }

    given("게임이 하나 주어졌다") {
        System.setIn("홍길동".byteInputStream())
        val game = BlackjackGame()

        `when`("해당 게임에서 유저가 히트를 하지 않으면") {
            System.setIn("N".byteInputStream())
            game.dealCards()
            then("카드가 추가되지 않는다") {
                game.users.first().deck.size shouldBe BlackjackGame.INITIAL_DECK_SIZE
            }
        }

        `when`("해당 게임에서 유저가 한번 히트를 하면") {
            System.setIn("Y\nN".byteInputStream())
            game.dealCards()
            then("카드가 한장 추가된다") {
                game.users.first().deck.size shouldBe BlackjackGame.INITIAL_DECK_SIZE + 1
            }
        }
    }

    given("게임에서 히트를 하면 21점을 넘기는 유저가 있다") {
        System.setIn("홍길동".byteInputStream())
        val game = BlackjackGame(
            FixedCardSelector(Card(Suit.SPADE, JackQueenKingCardNumber(11))),
        )

        `when`("해당 게임에서 딜을 할때") {
            then("유저는 히트를 한번밖에 하지 못한다") {
                System.setIn("Y".byteInputStream())
                game.dealCards()
                game.users.first().deck.size shouldBe BlackjackGame.INITIAL_DECK_SIZE + 1
            }
        }
    }
})
