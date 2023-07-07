package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.result.BlackjackResults
import blackjack.domain.result.PlayerResult
import blackjack.domain.result.PlayerResults
import blackjack.domain.user.UserDrawChecker
import blackjack.domain.user.Users
import blackjack.util.Dealer
import blackjack.util.User
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class BlackjackResultTest : BehaviorSpec({
    val userDrawChecker = UserDrawChecker { false }

    Given("21을 초과한 딜러가 있다") {
        val dealer = Dealer(
            listOf(
                Suit.SPADE to CardNumber.EIGHT,
                Suit.DIAMOND to CardNumber.EIGHT,
            ),
        )
        dealer.addCard(Card(Suit.HEART, CardNumber.EIGHT))

        forAll(
            table(
                headers("유저"),
                row(
                    User(
                        "블랙잭",
                        listOf(Suit.SPADE to CardNumber.ACE, Suit.SPADE to CardNumber.JACK),
                        userDrawChecker,
                    ),
                ),
                row(
                    User(
                        "블랙잭_아님",
                        listOf(Suit.HEART to CardNumber.JACK, Suit.SPADE to CardNumber.SEVEN),
                        userDrawChecker,
                    ),
                ),
            ),
        ) { user ->
            user.isHit() shouldBe false
            val users = Users(setOf(user))

            When("딜러와 \"${user.name}\"유저의 결과를 가져오면") {
                Then("유저가 승리했다") {
                    BlackjackResults(dealer, users).userResults shouldBe PlayerResults(
                        listOf(
                            PlayerResult(
                                user,
                                user.betMoney,
                            ),
                        ),
                    )
                }
            }
        }

        When("딜러와 \"21을 초과한\"유저의 결과를 가져오면") {
            val user = User(
                "초과함",
                listOf(
                    Suit.SPADE to CardNumber.TEN,
                    Suit.DIAMOND to CardNumber.TEN,
                ),
                userDrawChecker,
            )
            user.addCard(Card(Suit.HEART, CardNumber.TEN))
            user.isHit() shouldBe false
            val users = Users(setOf(user))
            Then("딜러가 승리했다") {
                BlackjackResults(dealer, users).userResults shouldBe PlayerResults(
                    listOf(
                        PlayerResult(
                            user,
                            -user.betMoney,
                        ),
                    ),
                )
            }
        }
    }

    Given("21을 초과하지 않은 딜러가 있다") {
        val dealer = Dealer(listOf(Suit.SPADE to CardNumber.EIGHT, Suit.DIAMOND to CardNumber.NINE))
        dealer.isHit() shouldBe false
        val userBetMoney = 10000

        forAll(
            table(
                headers("유저", "예상 딜러결과"),
                row(
                    User(
                        "블랙잭",
                        listOf(Suit.SPADE to CardNumber.ACE, Suit.SPADE to CardNumber.JACK),
                        userDrawChecker,
                        userBetMoney,
                    ),
                    PlayerResult(dealer, (userBetMoney * -1.5).toInt()),
                ),
                row(
                    User(
                        "딜러에게_짐",
                        listOf(Suit.DIAMOND to CardNumber.JACK, Suit.SPADE to CardNumber.SIX),
                        userDrawChecker,
                    ),
                    PlayerResult(dealer, userBetMoney),
                ),
                row(
                    User(
                        "초과함",
                        cardPairs = listOf(
                            Suit.SPADE to CardNumber.TEN,
                            Suit.DIAMOND to CardNumber.TEN,
                        ),
                        userDrawChecker,
                    ).apply { addCard(Card(Suit.HEART, CardNumber.TEN)) },
                    PlayerResult(dealer, userBetMoney),
                ),
            ),
        ) { user, dealerResult ->
            user.isHit() shouldBe false
            val users = Users(setOf(user))

            When("딜러와 \"${user.name}\"유저의 결과를 가져오면") {
                Then("예상결과대로 나온다") {
                    BlackjackResults(dealer, users).dealerResult shouldBe dealerResult
                }
            }
        }
    }

    Given("블랙잭인 딜러가 있다") {
        val dealer = Dealer(listOf(Suit.DIAMOND to CardNumber.ACE, Suit.DIAMOND to CardNumber.JACK))
        dealer.isHit() shouldBe false
        val userBetMoney = 10000

        forAll(
            table(
                headers("유저", "예상 딜러결과"),
                row(
                    User(
                        "블랙잭",
                        listOf(Suit.SPADE to CardNumber.ACE, Suit.SPADE to CardNumber.JACK),
                        userDrawChecker,
                        userBetMoney,
                    ),
                    PlayerResult(dealer, 0),
                ),
                row(
                    User(
                        "블랙잭은 아니지만 21점",
                        listOf(
                            Suit.HEART to CardNumber.JACK,
                            Suit.SPADE to CardNumber.SEVEN,
                            Suit.SPADE to CardNumber.FOUR,
                        ),
                        userDrawChecker,
                    ),
                    PlayerResult(dealer, userBetMoney),
                ),
                row(
                    User(
                        "딜러에게_짐",
                        listOf(Suit.DIAMOND to CardNumber.JACK, Suit.SPADE to CardNumber.SIX),
                        userDrawChecker,
                    ),
                    PlayerResult(dealer, userBetMoney),
                ),
                row(
                    User(
                        "초과함",
                        cardPairs = listOf(
                            Suit.SPADE to CardNumber.TEN,
                            Suit.DIAMOND to CardNumber.TEN,
                        ),
                        userDrawChecker,
                    ).apply { addCard(Card(Suit.HEART, CardNumber.TEN)) },
                    PlayerResult(dealer, userBetMoney),
                ),
            ),
        ) { user, dealerResult ->
            user.isHit() shouldBe false
            val users = Users(setOf(user))

            When("딜러와 \"${user.name}\"유저의 결과를 가져오면") {
                Then("예상결과대로 나온다") {
                    BlackjackResults(dealer, users).dealerResult shouldBe dealerResult
                }
            }
        }
    }
})
