package blackjack.domain

import blackjack.util.TEST_USER_DRAW_INTERFACE
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class UserTest : BehaviorSpec({
    val deck = Deck(listOf(Card(Suit.SPADE, CardNumber.ACE)))

    Given("비어있는 이름이 주어졌다") {
        val name = "  "
        When("해당 이름으로 User를 생성하면") {
            Then("에러가 던져진다.") {
                shouldThrow<IllegalArgumentException> { User(name, deck, TEST_USER_DRAW_INTERFACE) }
            }
        }
    }

    Given("정상적인 정보가 주어졌다") {
        val name = "홍길동"
        When("해당 이름으로 User를 생성하면") {
            Then("정상적으로 생성된다") {
                User(name, deck, TEST_USER_DRAW_INTERFACE).name shouldBe name
            }
        }
    }

    Given("21점을 넘기지 않은 덱을 가지고 있는 유저가 있다") {
        val user = User(
            "홍길동",
            Deck(listOf(Card(Suit.SPADE, CardNumber.ACE), Card(Suit.SPADE, CardNumber.TEN))),
            TEST_USER_DRAW_INTERFACE,
        )
        When("유저의 점수를 계산하면") {
            Then("버스트 상태가 아니다") {
                user.isBust() shouldBe false
            }
        }
    }

    Given("21점을 넘긴 덱을 가지고 있는 유저가 있다") {
        val user = User(
            "홍길동",
            Deck(
                listOf(
                    Card(Suit.SPADE, CardNumber.JACK),
                    Card(Suit.SPADE, CardNumber.QUEEN),
                    Card(Suit.HEART, CardNumber.KING),
                ),
            ),
            TEST_USER_DRAW_INTERFACE,
        )
        When("유저의 점수를 계산하면") {
            Then("버스트 상태가 된다") {
                user.isBust() shouldBe true
            }
        }
    }
})
