package domain

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Deck
import blackjack.domain.Participant
import blackjack.domain.Suit
import blackjack.domain.WinningMoney
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
                val sut = WinningMoney(dealer, player)
                val actual = sut.calculate()
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
                val sut = WinningMoney(dealer, player)
                sut.calculate()
                sut.calculate() shouldBe 1000.0
            }
        }
    }
})
