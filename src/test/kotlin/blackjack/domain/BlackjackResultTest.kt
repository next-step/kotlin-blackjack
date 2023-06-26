package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class BlackjackResultTest : BehaviorSpec({
    Given("21을 초과한 딜러가 있다") {
        val dealer = Dealer(Deck(listOf(Card(Suit.SPADE, CardNumber.EIGHT), Card(Suit.DIAMOND, CardNumber.EIGHT), Card(Suit.HEART, CardNumber.EIGHT))))

        forAll(
            table(
                headers("유저"),
                row(User("블랙잭", Deck(listOf(Card(Suit.SPADE, CardNumber.ACE), Card(Suit.SPADE, CardNumber.JACK))))),
                row(User("블랙잭_아님", Deck(listOf(Card(Suit.HEART, CardNumber.JACK), Card(Suit.SPADE, CardNumber.SEVEN))))),
                row(User("초과함", Deck(listOf(Card(Suit.SPADE, CardNumber.TEN), Card(Suit.DIAMOND, CardNumber.TEN), Card(Suit.HEART, CardNumber.TEN))))),
            ),
        ) { user ->
            val users = Users(setOf(user))

            When("딜러와 \"${user.name}\"유저의 결과를 가져오면") {
                Then("유저가 승리했다") {
                    BlackjackResults(dealer, users).dealerResult shouldBe DealerResult(dealer, 0, 0, 1)
                }
            }
        }
    }

    Given("21을 초과하지 않은 딜러가 있다") {
        val dealer = Dealer(Deck(listOf(Card(Suit.SPADE, CardNumber.EIGHT), Card(Suit.DIAMOND, CardNumber.NINE))))

        forAll(
            table(
                headers("유저", "예상결과"),
                row(User("블랙잭", Deck(listOf(Card(Suit.SPADE, CardNumber.ACE), Card(Suit.SPADE, CardNumber.JACK)))), DealerResult(dealer, 0, 0, 1)),
                row(User("딜러와_동점", Deck(listOf(Card(Suit.HEART, CardNumber.JACK), Card(Suit.SPADE, CardNumber.SEVEN)))), DealerResult(dealer, 0, 1, 0)),
                row(User("딜러에게_짐", Deck(listOf(Card(Suit.DIAMOND, CardNumber.JACK), Card(Suit.SPADE, CardNumber.SIX)))), DealerResult(dealer, 1, 0, 0)),
                row(User("초과함", Deck(listOf(Card(Suit.SPADE, CardNumber.TEN), Card(Suit.DIAMOND, CardNumber.TEN), Card(Suit.HEART, CardNumber.TEN)))), DealerResult(dealer, 1, 0, 0)),
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
