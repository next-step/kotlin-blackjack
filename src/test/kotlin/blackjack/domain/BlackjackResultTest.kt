package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Cards
import blackjack.domain.card.Suit
import blackjack.domain.result.BlackjackResults
import blackjack.domain.result.DealerResult
import blackjack.domain.user.Dealer
import blackjack.domain.user.User
import blackjack.domain.user.Users
import blackjack.util.TEST_USER_DRAW_INTERFACE
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class BlackjackResultTest : BehaviorSpec({
    Given("21을 초과한 딜러가 있다") {
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(Suit.SPADE, CardNumber.EIGHT),
                    Card(Suit.DIAMOND, CardNumber.EIGHT),
                    Card(Suit.HEART, CardNumber.EIGHT),
                ),
            ),
        )

        forAll(
            table(
                headers("유저"),
                row(
                    User(
                        "블랙잭",
                        Cards(listOf(Card(Suit.SPADE, CardNumber.ACE), Card(Suit.SPADE, CardNumber.JACK))),
                        TEST_USER_DRAW_INTERFACE,
                    ),
                ),
                row(
                    User(
                        "블랙잭_아님",
                        Cards(listOf(Card(Suit.HEART, CardNumber.JACK), Card(Suit.SPADE, CardNumber.SEVEN))),
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
                Cards(
                    listOf(
                        Card(Suit.SPADE, CardNumber.TEN),
                        Card(Suit.DIAMOND, CardNumber.TEN),
                        Card(Suit.HEART, CardNumber.TEN),
                    ),
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
        val dealer = Dealer(Cards(listOf(Card(Suit.SPADE, CardNumber.EIGHT), Card(Suit.DIAMOND, CardNumber.NINE))))

        forAll(
            table(
                headers("유저", "예상결과"),
                row(
                    User(
                        "블랙잭",
                        Cards(listOf(Card(Suit.SPADE, CardNumber.ACE), Card(Suit.SPADE, CardNumber.JACK))),
                        TEST_USER_DRAW_INTERFACE,
                    ),
                    DealerResult(dealer, 0, 0, 1),
                ),
                row(
                    User(
                        "딜러와_동점",
                        Cards(listOf(Card(Suit.HEART, CardNumber.JACK), Card(Suit.SPADE, CardNumber.SEVEN))),
                        TEST_USER_DRAW_INTERFACE,
                    ),
                    DealerResult(dealer, 0, 1, 0),
                ),
                row(
                    User(
                        "딜러에게_짐",
                        Cards(listOf(Card(Suit.DIAMOND, CardNumber.JACK), Card(Suit.SPADE, CardNumber.SIX))),
                        TEST_USER_DRAW_INTERFACE,
                    ),
                    DealerResult(dealer, 1, 0, 0),
                ),
                row(
                    User(
                        "초과함",
                        Cards(
                            listOf(
                                Card(Suit.SPADE, CardNumber.TEN),
                                Card(Suit.DIAMOND, CardNumber.TEN),
                                Card(Suit.HEART, CardNumber.TEN),
                            ),
                        ),
                        TEST_USER_DRAW_INTERFACE,
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
