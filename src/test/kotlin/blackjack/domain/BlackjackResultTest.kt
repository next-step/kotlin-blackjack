package blackjack.domain

import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.result.BlackjackResults
import blackjack.domain.result.DealerResult
import blackjack.domain.user.Users
import blackjack.util.Dealer
import blackjack.util.TEST_USER_DRAW_INTERFACE
import blackjack.util.User
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class BlackjackResultTest : BehaviorSpec({
    Given("21을 초과한 딜러가 있다") {
        val dealer = Dealer(
            listOf(
                Suit.SPADE to CardNumber.EIGHT,
                Suit.DIAMOND to CardNumber.EIGHT,
                Suit.HEART to CardNumber.EIGHT,
            ),
        )

        forAll(
            table(
                headers("유저"),
                row(
                    User(
                        "블랙잭",
                        listOf(Suit.SPADE to CardNumber.ACE, Suit.SPADE to CardNumber.JACK),
                        TEST_USER_DRAW_INTERFACE,
                    ),
                ),
                row(
                    User(
                        "블랙잭_아님",
                        listOf(Suit.HEART to CardNumber.JACK, Suit.SPADE to CardNumber.SEVEN),
                        TEST_USER_DRAW_INTERFACE,
                    ),
                ),
            ),
        ) { user ->
            val users = Users(setOf(user))

            When("딜러와 \"${user.name}\"유저의 결과를 가져오면") {
                Then("유저가 승리했다") {
                    BlackjackResults(dealer, users).dealerResult shouldBe DealerResult(dealer, 0, 0, 1)
                }
            }
        }

        When("딜러와 \"21을 초과한\"유저의 결과를 가져오면") {
            val user = User(
                "초과함",
                listOf(
                    Suit.SPADE to CardNumber.TEN,
                    Suit.DIAMOND to CardNumber.TEN,
                    Suit.HEART to CardNumber.TEN,
                ),
                TEST_USER_DRAW_INTERFACE,
            )
            val users = Users(setOf(user))
            Then("딜러가 승리했다") {
                BlackjackResults(dealer, users).dealerResult shouldBe DealerResult(dealer, 1, 0, 0)
            }
        }
    }

    Given("21을 초과하지 않은 딜러가 있다") {
        val dealer = Dealer(listOf(Suit.SPADE to CardNumber.EIGHT, Suit.DIAMOND to CardNumber.NINE))

        forAll(
            table(
                headers("유저", "예상결과"),
                row(
                    User(
                        "블랙잭",
                        listOf(Suit.SPADE to CardNumber.ACE, Suit.SPADE to CardNumber.JACK),
                        TEST_USER_DRAW_INTERFACE,
                    ),
                    DealerResult(dealer, 0, 0, 1),
                ),
                row(
                    User(
                        "딜러와_동점",
                        listOf(Suit.HEART to CardNumber.JACK, Suit.SPADE to CardNumber.SEVEN),
                        TEST_USER_DRAW_INTERFACE,
                    ),
                    DealerResult(dealer, 0, 1, 0),
                ),
                row(
                    User(
                        "딜러에게_짐",
                        listOf(Suit.DIAMOND to CardNumber.JACK, Suit.SPADE to CardNumber.SIX),
                    ),
                    DealerResult(dealer, 1, 0, 0),
                ),
                row(
                    User(
                        "초과함",
                        cardPairs = listOf(
                            Suit.SPADE to CardNumber.TEN,
                            Suit.DIAMOND to CardNumber.TEN,
                            Suit.HEART to CardNumber.TEN,
                        ),
                    ),
                    DealerResult(dealer, 1, 0, 0),
                ),
            ),
        ) { user, dealerResult ->
            val users = Users(setOf(user))

            When("딜러와 \"${user.name}\"유저의 결과를 가져오면") {
                Then("예상결과대로 나온다") {
                    BlackjackResults(dealer, users).dealerResult shouldBe dealerResult
                }
            }
        }
    }
})
