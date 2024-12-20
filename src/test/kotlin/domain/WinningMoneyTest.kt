package domain

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Deck
import blackjack.domain.EarningMoney
import blackjack.domain.Participant
import blackjack.domain.Suit
import fixture.CardListFixture
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class WinningMoneyTest : DescribeSpec({
    describe("calculate test") {
        context("플레이어만 블랙잭인 경우") {
            it("베팅 금액의 1.5배를 받는다") {
                val player =
                    Participant.Player(
                        "pobi",
                        1000,
                        mutableListOf(Card(Suit.DIAMONDS, CardNumber.ACE), Card(Suit.DIAMONDS, CardNumber.KING)),
                    )
                val dealer =
                    Participant.Dealer(
                        Deck(CardListFixture.blackjackCardList()),
                        mutableListOf(Card(Suit.CLUBS, CardNumber.ACE), Card(Suit.CLUBS, CardNumber.TWO)),
                    )
                val sut = EarningMoney(dealer)
                val actual = sut.calculate(player)
                actual shouldBe 1500.0
            }
        }

        context("플래에어와 딜러가 모두 블랙잭인 경우") {
            it("베팅 금액을 받는다") {
                val player =
                    Participant.Player(
                        "pobi",
                        1000,
                        mutableListOf(Card(Suit.DIAMONDS, CardNumber.ACE), Card(Suit.DIAMONDS, CardNumber.KING)),
                    )
                val dealer =
                    Participant.Dealer(
                        Deck(CardListFixture.blackjackCardList()),
                        mutableListOf(Card(Suit.CLUBS, CardNumber.ACE), Card(Suit.CLUBS, CardNumber.KING)),
                    )
                val sut = EarningMoney(dealer)
                sut.calculate(player) shouldBe 1000.0
            }
        }

        context("플레이어가 bust 된 경우") {
            it("배팅 금액을 잃는다.") {
                val player =
                    Participant.Player(
                        "pobi",
                        1000,
                        mutableListOf(
                            Card(Suit.DIAMONDS, CardNumber.JACK),
                            Card(Suit.DIAMONDS, CardNumber.KING),
                            Card(Suit.DIAMONDS, CardNumber.QUEEN),
                        ),
                    )
                val dealer =
                    Participant.Dealer(
                        Deck(CardListFixture.blackjackCardList()),
                        mutableListOf(Card(Suit.CLUBS, CardNumber.ACE), Card(Suit.CLUBS, CardNumber.KING)),
                    )
                val sut = EarningMoney(dealer)
                sut.calculate(player) shouldBe -1000.0
            }
        }
    }
})
