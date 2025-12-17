package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({
    Given("김효건 플레이어에게") {
        val player = Player("김효건")
        When("다이아몬드 에이스 카드를 더하면") {
            player.addCard(Card(Suit.DIAMOND, Rank.ACE))
            Then("카드리스트에 카드가 추가된다.") {
                player.cards.size shouldBe 1
            }
            Then("총 점수가 11점이 된다.") {
                player.totalScore() shouldBe 11
            }
        }
        When("하트 에이스 카드를 더하면") {
            player.addCard(Card(Suit.HEART, Rank.ACE))
            Then("카드가 총 2장이 된다.") {
                player.cards.size shouldBe 2
            }
            Then("총 점수가 12점이 된다.") {
                player.totalScore() shouldBe 12
            }
        }
        When("하트 클로바 5 카드를 더하면") {
            player.addCard(Card(Suit.HEART, Rank.FIVE))
            Then("카드리스트에 카드가 또 추가된다.") {
                player.cards.size shouldBe 3
            }
            Then("총 점수가 17점이 된다.") {
                player.totalScore() shouldBe 17
            }
        }
    }

    Given("플레이어에 다음과 같이 카드가 분배 됐을 때") {
        val player = Player("김효건")
        player.addCard(Card(Suit.DIAMOND, Rank.ACE))
        player.addCard(Card(Suit.HEART, Rank.ACE))
        player.addCard(Card(Suit.HEART, Rank.FIVE))

        When("딜러와 점수는 같은데, 카드 수는 적으면") {
            val dealer = Dealer()
            dealer.addCard(Card(Suit.DIAMOND, Rank.ACE))
            dealer.addCard(Card(Suit.CLUB, Rank.THREE))
            dealer.addCard(Card(Suit.HEART, Rank.ACE))
            dealer.addCard(Card(Suit.SPADE, Rank.TWO))
            Then("이긴다.") {
                player.match(dealer) shouldBe RecordType.WIN
            }
        }

        When("딜러와 점수는 같고, 카드 수도 같으면") {
            val dealer = Dealer()
            dealer.addCard(Card(Suit.HEART, Rank.QUEEN))
            dealer.addCard(Card(Suit.SPADE, Rank.FIVE))
            dealer.addCard(Card(Suit.CLUB, Rank.TWO))
            Then("비긴다.") {
                player.match(dealer) shouldBe RecordType.DRAW
            }
        }

        When("딜러보다 점수가 높으면") {
            val dealer = Dealer()
            dealer.addCard(Card(Suit.DIAMOND, Rank.ACE))
            dealer.addCard(Card(Suit.CLUB, Rank.THREE))
            dealer.addCard(Card(Suit.HEART, Rank.ACE))
            dealer.addCard(Card(Suit.SPADE, Rank.ACE))
            Then("이긴다.") {
                player.match(dealer) shouldBe RecordType.WIN
            }
        }

        When("딜러보다 점수가 낮으면") {
            val dealer = Dealer()
            dealer.addCard(Card(Suit.DIAMOND, Rank.ACE))
            dealer.addCard(Card(Suit.CLUB, Rank.SIX))
            dealer.addCard(Card(Suit.HEART, Rank.ACE))
            dealer.addCard(Card(Suit.SPADE, Rank.ACE))
            Then("진다.") {
                player.match(dealer) shouldBe RecordType.LOSE
            }
        }
    }
})
