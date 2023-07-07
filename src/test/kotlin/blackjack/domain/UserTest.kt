package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Cards
import blackjack.domain.card.Suit
import blackjack.domain.user.PlayerStatus
import blackjack.domain.user.User
import blackjack.domain.user.UserDrawChecker
import blackjack.util.TEST_USER_DRAW_CHECKER
import blackjack.util.User
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class UserTest : BehaviorSpec({
    val name = "홍길동"
    val cards = Cards(listOf(Card(Suit.SPADE, CardNumber.ACE)))
    val betMoney = 10000

    Given("비어있는 이름이 주어졌다") {
        val emptyName = "  "
        When("해당 이름으로 User를 생성하면") {
            Then("에러가 던져진다.") {
                shouldThrow<IllegalArgumentException> { User(emptyName, cards, TEST_USER_DRAW_CHECKER, betMoney) }
            }
        }
    }

    Given("배팅금액이 0원 이하로 주어졌다") {
        When("해당 금액으로 User를 생성하면") {
            Then("에러가 던져진다.") {
                shouldThrow<IllegalArgumentException> { User(name, cards, TEST_USER_DRAW_CHECKER, 0) }
            }
        }
    }

    Given("정상적인 정보가 주어졌다") {
        When("해당 이름으로 User를 생성하면") {
            Then("정상적으로 생성된다") {
                shouldNotThrowAny { User(name, cards, TEST_USER_DRAW_CHECKER, betMoney) }
            }
        }
    }

    Given("Hit상태의 유저가 있다") {
        val userDrawChecker = UserDrawChecker { false }
        val user = User(name, cards, userDrawChecker, betMoney)
        user.status shouldBe PlayerStatus.HIT
        When("Stand를 하면") {
            user.isHit() shouldBe false
            Then("Stand상태가 된다") {
                user.status shouldBe PlayerStatus.STAND
            }
        }
    }

    Given("21점을 넘기지 않은 덱을 가지고 있는 유저가 있다") {
        val user = User(
            name,
            listOf(Suit.SPADE to CardNumber.ACE, Suit.SPADE to CardNumber.TEN),
            TEST_USER_DRAW_CHECKER,
        )
        When("유저의 점수를 계산하면") {
            Then("버스트 상태가 아니다") {
                user.isBust() shouldBe false
            }
        }
    }

    Given("블랙잭 상태의 유저가 있다") {
        val user = User(
            name,
            listOf(Suit.SPADE to CardNumber.ACE, Suit.SPADE to CardNumber.TEN),
            TEST_USER_DRAW_CHECKER,
        )
        user.status shouldBe PlayerStatus.BLACKJACK
        When("해당 유저가 카드를 한장 더 받으면") {
            user.addCard(Card(Suit.HEART, CardNumber.KING))
            Then("Hit상태가 된다") {
                user.status shouldBe PlayerStatus.HIT
            }
        }
    }

    Given("Stand상태의 유저가 있다") {
        val userDrawChecker = UserDrawChecker { false }
        val user = User(
            name,
            listOf(Suit.SPADE to CardNumber.ACE, Suit.SPADE to CardNumber.NINE),
            userDrawChecker,
        )
        user.isHit() shouldBe false
        user.status shouldBe PlayerStatus.STAND
        When("해당 유저에게 카드를 한장더 주면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalStateException> { user.addCard(Card(Suit.HEART, CardNumber.KING)) }
            }
        }
    }

    Given("Bust상태의 유저가 있다") {
        val user = User(
            name,
            listOf(Suit.SPADE to CardNumber.TWO, Suit.SPADE to CardNumber.TEN),
            TEST_USER_DRAW_CHECKER,
        )
        user.addCard(Card(Suit.HEART, CardNumber.KING))
        user.status shouldBe PlayerStatus.BUST
        When("해당 유저에게 카드를 한장더 주면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalStateException> { user.addCard(Card(Suit.HEART, CardNumber.QUEEN)) }
            }
        }
    }

    Given("21점을 넘기지 않은 덱을 가진 유저가 있다") {
        val user = User(
            name,
            listOf(
                Suit.SPADE to CardNumber.JACK,
                Suit.SPADE to CardNumber.QUEEN,
            ),
            TEST_USER_DRAW_CHECKER,
        )
        user.isBust() shouldBe false
        When("해당 유저가 카드를 한장 더 받으면") {
            user.addCard(Card(Suit.HEART, CardNumber.KING))
            Then("버스트 상태가 된다") {
                user.isBust() shouldBe true
            }
        }
    }

    Given("블랙잭이 아닌 유저가") {
        val user = User(
            name,
            listOf(
                Suit.SPADE to CardNumber.FIVE,
                Suit.DIAMOND to CardNumber.FIVE,
            ),
            TEST_USER_DRAW_CHECKER,
        )
        When("카드를 한장더 받아서 21점이 되어도") {
            user.addCard(Card(Suit.SPADE, CardNumber.ACE))
            Then("블랙잭 상태가 되지 않는다") {
                user.status shouldNotBe PlayerStatus.BLACKJACK
            }
        }
    }

    Given("A유저가 B유저에게 지는 상황이 주어졌다") {
        val userA = User(
            "A",
            listOf(
                Suit.SPADE to CardNumber.FIVE,
                Suit.DIAMOND to CardNumber.FIVE,
            ),
        )
        val userB = User(
            "B",
            listOf(
                Suit.SPADE to CardNumber.SIX,
                Suit.DIAMOND to CardNumber.SIX,
            ),
        )
        When("A 수익을 확인하면") {
            Then("자신의 배팅금액을 잃었다") {
                userA.getUserProfit(userB) shouldBe -userA.betMoney
            }
        }
    }

    Given("A유저가 B유저와 비기는 상황이다") {
        val userA = User(
            "A",
            listOf(
                Suit.SPADE to CardNumber.FIVE,
                Suit.DIAMOND to CardNumber.FIVE,
            ),
        )
        val userB = User(
            "B",
            listOf(
                Suit.HEART to CardNumber.FIVE,
                Suit.CLOVER to CardNumber.FIVE,
            ),
        )
        When("A 수익을 확인하면") {
            Then("수익이 없다") {
                userA.getUserProfit(userB) shouldBe 0
            }
        }
    }

    Given("A유저가 B유저에게 이기는 상황이 주어졌다") {
        val userA = User(
            "A",
            listOf(
                Suit.SPADE to CardNumber.SIX,
                Suit.DIAMOND to CardNumber.SIX,
            ),
        )
        val userB = User(
            "B",
            listOf(
                Suit.HEART to CardNumber.FIVE,
                Suit.CLOVER to CardNumber.FIVE,
            ),
        )
        When("A 수익을 확인하면") {
            Then("배팅금액만큼 벌었다") {
                userA.getUserProfit(userB) shouldBe userA.betMoney
            }
        }
    }

    Given("A유저가 B유저에게 블랙잭으로 이기는 상황이 주어졌다") {
        val userA = User(
            "A",
            listOf(
                Suit.SPADE to CardNumber.ACE,
                Suit.DIAMOND to CardNumber.TEN,
            ),
        )
        val userB = User(
            "B",
            listOf(
                Suit.HEART to CardNumber.FIVE,
                Suit.CLOVER to CardNumber.FIVE,
            ),
        )
        When("A 수익을 확인하면") {
            Then("배팅금액의 1.5배를 벌었다") {
                userA.getUserProfit(userB) shouldBe (userA.betMoney * 1.5).toInt()
            }
        }
    }

    Given("A유저가 B유저와 블랙잭으로 비기는 상황이 주어졌다") {
        val userA = User(
            "A",
            listOf(
                Suit.SPADE to CardNumber.ACE,
                Suit.DIAMOND to CardNumber.TEN,
            ),
        )
        val userB = User(
            "B",
            listOf(
                Suit.HEART to CardNumber.ACE,
                Suit.CLOVER to CardNumber.TEN,
            ),
        )
        When("A 수익을 확인하면") {
            Then("수익이 없다") {
                userA.getUserProfit(userB) shouldBe 0
            }
        }
    }
})
