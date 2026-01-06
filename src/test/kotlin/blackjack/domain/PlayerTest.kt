package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({
    Given("김효건 플레이어에게") {
        val player = Player("김효건")
        When("다이아몬드 에이스 카드를 더하면") {
            player.addCard(Card(Suit.DIAMOND, Rank.ACE))
            Then("총 점수가 11점이 된다.") {
                player.score() shouldBe 11
            }
        }
        When("하트 에이스 카드를 더하면") {
            player.addCard(Card(Suit.HEART, Rank.ACE))
            Then("총 점수가 12점이 된다.") {
                player.score() shouldBe 12
            }
        }
        When("하트 5 카드를 더하면") {
            player.addCard(Card(Suit.HEART, Rank.FIVE))
            Then("총 점수가 17점이 된다.") {
                player.score() shouldBe 17
            }
        }
    }

    Given("플레이어에 다음과 같이 2장의 카드가 분배 됐을 때") {
        val player = Player("김효건")
        player.placeBet(BetMoney(1000))
        player.addCard(Card(Suit.DIAMOND, Rank.ACE))
        player.addCard(Card(Suit.HEART, Rank.FIVE))

        When("딜러와 점수는 같은데, 카드 수는 적으면") {
            val dealer = Dealer()
            dealer.addCard(Card(Suit.DIAMOND, Rank.ACE))
            dealer.addCard(Card(Suit.CLUB, Rank.THREE))
            dealer.addCard(Card(Suit.HEART, Rank.TWO))
            Then("비겼기때문에 수익은 없다") {
                player.profit(dealer) shouldBe 0.0
            }
        }

        When("딜러와 점수는 같고, 카드 수도 같으면") {
            val dealer = Dealer()
            dealer.addCard(Card(Suit.HEART, Rank.ACE))
            dealer.addCard(Card(Suit.SPADE, Rank.FIVE))
            Then("비겼기때문에 수익은 없다") {
                player.profit(dealer) shouldBe 0.0
            }
        }

        When("딜러보다 점수가 높으면") {
            val dealer = Dealer()
            dealer.addCard(Card(Suit.DIAMOND, Rank.ACE))
            dealer.addCard(Card(Suit.CLUB, Rank.TWO))
            dealer.addCard(Card(Suit.HEART, Rank.TWO))
            Then("이겼기때문에 수익은 배팅한 금액이다.") {
                player.profit(dealer) shouldBe 1000.0
            }
        }

        When("딜러보다 점수가 낮으면") {
            val dealer = Dealer()
            dealer.addCard(Card(Suit.DIAMOND, Rank.ACE))
            dealer.addCard(Card(Suit.CLUB, Rank.THREE))
            dealer.addCard(Card(Suit.HEART, Rank.THREE))
            Then("졌기때문에 배팅한 금액을 잃는다.") {
                player.profit(dealer) shouldBe -1000.0
            }
        }
    }

    Given("플레이어에 다음과 같이 3장의 카드가 분배 됐을 때") {
        val player = Player("김효건")
        player.placeBet(BetMoney(1000))
        player.addCard(Card(Suit.DIAMOND, Rank.ACE))
        player.addCard(Card(Suit.HEART, Rank.FIVE))
        player.addCard(Card(Suit.HEART, Rank.ACE))

        When("딜러와 점수는 같고, 카드 수도 같으면") {
            val dealer = Dealer()
            dealer.addCard(Card(Suit.HEART, Rank.FOUR))
            dealer.addCard(Card(Suit.SPADE, Rank.FIVE))
            dealer.addCard(Card(Suit.SPADE, Rank.EIGHT))
            Then("비겼기때문에 수익은 없다") {
                player.profit(dealer) shouldBe 0.0
            }
        }

        When("딜러보다 점수가 높으면") {
            val dealer = Dealer()
            dealer.addCard(Card(Suit.DIAMOND, Rank.ACE))
            dealer.addCard(Card(Suit.HEART, Rank.FOUR))
            dealer.addCard(Card(Suit.HEART, Rank.ACE))
            Then("이겼기때문에 수익은 배팅한 금액이다.") {
                player.profit(dealer) shouldBe 1000.0
            }
        }

        When("딜러보다 점수가 낮으면") {
            val dealer = Dealer()
            dealer.addCard(Card(Suit.DIAMOND, Rank.ACE))
            dealer.addCard(Card(Suit.HEART, Rank.FIVE))
            dealer.addCard(Card(Suit.HEART, Rank.TWO))
            Then("졌기때문에 배팅한 금액을 잃는다.") {
                player.profit(dealer) shouldBe -1000.0
            }
        }

        When("딜러와 점수는 같고, 카드 수도 많으면") {
            val dealer = Dealer()
            dealer.addCard(Card(Suit.HEART, Rank.ACE))
            dealer.addCard(Card(Suit.SPADE, Rank.SIX))
            Then("비겼기때문에 수익은 없다") {
                player.profit(dealer) shouldBe 0.0
            }
        }
    }

    Given("플레이어가 버스트(21 초과)했을 때") {
        val player = Player("김효건")
        player.placeBet(BetMoney(1000))
        player.addCard(Card(Suit.DIAMOND, Rank.TEN))
        player.addCard(Card(Suit.CLUB, Rank.TEN))
        player.addCard(Card(Suit.HEART, Rank.TWO))

        When("딜러 점수와 관계없이") {
            val dealer = Dealer()
            dealer.addCard(Card(Suit.DIAMOND, Rank.TEN))
            dealer.addCard(Card(Suit.CLUB, Rank.TEN))
            Then("졌기때문에 배팅한 금액을 잃는다.") {
                player.profit(dealer) shouldBe -1000.0
            }
        }

        When("딜러도 버스트했을 때") {
            val dealer = Dealer()
            dealer.addCard(Card(Suit.DIAMOND, Rank.TEN))
            dealer.addCard(Card(Suit.CLUB, Rank.TEN))
            dealer.addCard(Card(Suit.HEART, Rank.TWO))
            Then("졌기때문에 배팅한 금액을 잃는다.") {
                player.profit(dealer) shouldBe -1000.0
            }
        }
    }

    Given("딜러가 버스트(21 초과)했을 때") {
        val dealer = Dealer()
        dealer.addCard(Card(Suit.DIAMOND, Rank.TEN))
        dealer.addCard(Card(Suit.CLUB, Rank.TEN))
        dealer.addCard(Card(Suit.HEART, Rank.TWO))

        When("플레이어 점수와 관계없이") {
            val player = Player("김효건")
            player.placeBet(BetMoney(1000))
            player.addCard(Card(Suit.DIAMOND, Rank.TEN))
            player.addCard(Card(Suit.CLUB, Rank.TEN))
            Then("이겼기때문에 수익은 배팅한 금액이다.") {
                player.profit(dealer) shouldBe 1000.0
            }
        }

        When("플레이어가 블랙잭일 때") {
            val player = Player("김효건")
            player.placeBet(BetMoney(1000))
            player.addCard(Card(Suit.DIAMOND, Rank.ACE))
            player.addCard(Card(Suit.CLUB, Rank.TEN))
            Then("블랙잭이기 때문에 수익은 배팅한 금액 * 1.5 이다.") {
                player.profit(dealer) shouldBe 1500.0
            }
        }

        When("플레이어가 21점이지만 3장일 때") {
            val player = Player("김효건")
            player.placeBet(BetMoney(1000))
            player.addCard(Card(Suit.DIAMOND, Rank.TEN))
            player.addCard(Card(Suit.CLUB, Rank.SIX))
            player.addCard(Card(Suit.HEART, Rank.FIVE))
            Then("이겼기때문에 수익은 배팅한 금액이다.") {
                player.profit(dealer) shouldBe 1000.0
            }
        }
    }

    Given("플레이어가 블랙잭일 때") {
        val player = Player("김효건")
        player.placeBet(BetMoney(1000))
        player.addCard(Card(Suit.DIAMOND, Rank.ACE))
        player.addCard(Card(Suit.CLUB, Rank.TEN))

        When("딜러도 블랙잭일 때") {
            val dealer = Dealer()
            dealer.addCard(Card(Suit.HEART, Rank.ACE))
            dealer.addCard(Card(Suit.SPADE, Rank.TEN))
            Then("비겼기때문에 수익은 없다") {
                player.profit(dealer) shouldBe 0.0
            }
        }

        When("딜러가 블랙잭이 아닐 때") {
            val dealer = Dealer()
            dealer.addCard(Card(Suit.HEART, Rank.ACE))
            dealer.addCard(Card(Suit.SPADE, Rank.NINE))
            Then("블랙잭이기 때문에 수익은 배팅한 금액 * 1.5 이다.") {
                player.profit(dealer) shouldBe 1500.0
            }
        }

        When("딜러가 21점이지만 3장일 때") {
            val dealer = Dealer()
            dealer.addCard(Card(Suit.HEART, Rank.ACE))
            dealer.addCard(Card(Suit.SPADE, Rank.NINE))
            dealer.addCard(Card(Suit.CLUB, Rank.ACE))
            Then("블랙잭이기 때문에 수익은 배팅한 금액 * 1.5 이다.") {
                player.profit(dealer) shouldBe 1500.0
            }
        }
    }

    Given("플레이어와 딜러가 동점일 때") {
        When("둘 다 17점이고 카드 개수가 다를 때") {
            val player = Player("김효건")
            player.placeBet(BetMoney(1000))
            player.addCard(Card(Suit.DIAMOND, Rank.TEN))
            player.addCard(Card(Suit.CLUB, Rank.SEVEN))

            val dealer = Dealer()
            dealer.addCard(Card(Suit.HEART, Rank.TEN))
            dealer.addCard(Card(Suit.SPADE, Rank.FIVE))
            dealer.addCard(Card(Suit.CLUB, Rank.TWO))
            Then("비겼기때문에 수익은 없다") {
                player.profit(dealer) shouldBe 0.0
            }
        }

        When("둘 다 20점이고 카드 개수가 같을 때") {
            val player = Player("김효건")
            player.placeBet(BetMoney(1000))
            player.addCard(Card(Suit.DIAMOND, Rank.TEN))
            player.addCard(Card(Suit.CLUB, Rank.TEN))

            val dealer = Dealer()
            dealer.addCard(Card(Suit.HEART, Rank.TEN))
            dealer.addCard(Card(Suit.SPADE, Rank.TEN))
            Then("비겼기때문에 수익은 없다") {
                player.profit(dealer) shouldBe 0.0
            }
        }

        When("둘 다 18점이고 플레이어가 카드가 더 많을 때") {
            val player = Player("김효건")
            player.placeBet(BetMoney(1000))
            player.addCard(Card(Suit.DIAMOND, Rank.TEN))
            player.addCard(Card(Suit.CLUB, Rank.FIVE))
            player.addCard(Card(Suit.HEART, Rank.THREE))

            val dealer = Dealer()
            dealer.addCard(Card(Suit.HEART, Rank.TEN))
            dealer.addCard(Card(Suit.SPADE, Rank.EIGHT))
            Then("비겼기때문에 수익은 없다") {
                player.profit(dealer) shouldBe 0.0
            }
        }
    }
})
