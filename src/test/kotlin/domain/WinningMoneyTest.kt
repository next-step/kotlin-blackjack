package domain

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Deck
import blackjack.domain.EarningCalculator
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
                val sut = EarningCalculator(dealer)
                val actual = sut.playerMoney(player)
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
                val sut = EarningCalculator(dealer)
                sut.playerMoney(player) shouldBe 1000.0
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
                val sut = EarningCalculator(dealer)
                sut.playerMoney(player) shouldBe -1000.0
            }
        }

        context("딜러가 bust된 경우") {
            it("플레이어가 bust 되지 않았다면 돈을 받는다.") {
                val player =
                    Participant.Player(
                        "pobi",
                        1000,
                        mutableListOf(Card(Suit.CLUBS, CardNumber.ACE), Card(Suit.CLUBS, CardNumber.KING)),
                    )
                val dealer =
                    Participant.Dealer(
                        Deck(CardListFixture.blackjackCardList()),
                        mutableListOf(
                            Card(Suit.DIAMONDS, CardNumber.JACK),
                            Card(Suit.DIAMONDS, CardNumber.KING),
                            Card(Suit.DIAMONDS, CardNumber.QUEEN),
                        ),
                    )

                val sut = EarningCalculator(dealer)
                sut.playerMoney(player) shouldBe 2000.0
            }

            it("플레이어도 bust 되었다면 돈을 잃는다.") {
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
                        mutableListOf(
                            Card(Suit.DIAMONDS, CardNumber.JACK),
                            Card(Suit.DIAMONDS, CardNumber.KING),
                            Card(Suit.DIAMONDS, CardNumber.QUEEN),
                        ),
                    )

                val sut = EarningCalculator(dealer)
                sut.playerMoney(player) shouldBe -1000.0
            }
        }
    }

    describe("딜러가 얻은 상금을 계산한다.") {
        it("딜러가 얻은 돈은 플레이어가 잃은 돈과 같다.") {
            val player =
                Participant.Player(
                    "pobi",
                    1000,
                    mutableListOf(Card(Suit.CLUBS, CardNumber.ACE), Card(Suit.CLUBS, CardNumber.KING)),
                )
            val dealer =
                Participant.Dealer(
                    Deck(CardListFixture.blackjackCardList()),
                    mutableListOf(
                        Card(Suit.DIAMONDS, CardNumber.JACK),
                        Card(Suit.DIAMONDS, CardNumber.KING),
                        Card(Suit.DIAMONDS, CardNumber.QUEEN),
                    ),
                )

            val sut = EarningCalculator(dealer)
            sut.dealerMoney(listOf(player)) shouldBe -2000.0
        }
    }
})
