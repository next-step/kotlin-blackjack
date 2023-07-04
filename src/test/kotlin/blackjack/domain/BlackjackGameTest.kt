package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.user.UserNames
import blackjack.util.FixedCardsSelector
import blackjack.util.TEST_USER_BET_MONEY_GETTER
import blackjack.util.TEST_USER_DRAW_INTERFACE
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BlackjackGameTest : BehaviorSpec({

    Given("게임이 하나 주어졌다") {
        val cardSelector = FixedCardsSelector(
            Card(Suit.SPADE, CardNumber.ACE),
            Card(Suit.DIAMOND, CardNumber.ACE),
            Card(Suit.HEART, CardNumber.ACE),
            Card(Suit.CLOVER, CardNumber.ACE),
        )
        val userNames = UserNames(setOf("홍길동"))
        val game = BlackjackGame(userNames, TEST_USER_DRAW_INTERFACE, TEST_USER_BET_MONEY_GETTER, cardSelector)

        When("게임 시작시 유저의 패를 확인하면") {
            Then("2장을 가지고 있다") {
                val users = game.users
                val user = users.first()
                user.getCardsSize() shouldBe 2
            }
        }

        When("딜러의 패를 확인하면") {
            Then("2장을 가지고 있다") {
                game.dealer.getCardsSize() shouldBe 2
            }
        }
    }

    Given("초기의 딜러의 패 점수가 16점 이하로 주어지는 게임이 있다") {

        val userNames = UserNames(setOf("홍길동"))
        val cardSelector = FixedCardsSelector(
            Card(Suit.SPADE, CardNumber.EIGHT), // 딜러의 카드
            Card(Suit.DIAMOND, CardNumber.EIGHT), // 딜러의 카드
            Card(Suit.HEART, CardNumber.EIGHT), // 유저의 카드
            Card(Suit.CLOVER, CardNumber.EIGHT), // 유저의 카드
            Card(Suit.CLOVER, CardNumber.NINE),
        )
        val game = BlackjackGame(userNames, TEST_USER_DRAW_INTERFACE, TEST_USER_BET_MONEY_GETTER, cardSelector)
        When("딜러가 딜을 하면") {
            game.dealDealer()
            Then("딜러는 카드를 한장더 뽑는다") {
                game.dealer.getCardsSize() shouldBe 3
            }
        }
    }
})
