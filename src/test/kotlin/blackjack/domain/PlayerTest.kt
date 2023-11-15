package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({
    given("플레이어 이름이 주어지면") {
        val name = "원동재"
        `when`("플레이어 생성을 하면") {
            val player = Player(name)
            then("플레이어의 이름은 원동재가 된다.") {
                player.name shouldBe "원동재"
            }
        }
    }

    given("플레이어 이름과 트럼프 카드가 주어지고") {
        val name = "원동재"
        val trumpCard = TrumpCard.init()
        `when`("플레이어가 초기 카드를 받게되면") {
            val player = Player(name, trumpCard.drawFirstCards())
            then("플레이어의 카드는 2장이 된다.") {
                player.cards.cards.size shouldBe 2
            }
        }
    }

    given("트럼프 카드가 주어지고 플레이어가 2장을 받았다면") {
        val trumpCard = TrumpCard.init()
        val player = Player("원동재", trumpCard.drawFirstCards())
        `when`("플레이어 카드에 카드를 한장 더 뽑으면") {
            player.drawBy(trumpCard.draw())
            then("플레이어 카드는 3장이다.") {
                player.cards.cards.size shouldBe 3
            }
        }
    }

    given("카드 K다이아몬드, K스페이드를 받았다면") {
        val cards = Cards(
            Suit.DIAMOND to Rank.KING,
            Suit.SPADE to Rank.KING,
        )
        val player = Player("원동재", cards)
        When("2다이아몬드를 뽑았을때") {
            player.drawBy(Card(Suit.DIAMOND, Rank.TWO))
            val isBurst = player.isBurst()
            Then("플레이어 상태는 버스트이다.") {
                isBurst shouldBe true
            }
        }
    }

    given("K스페이드, 9다이아몬드를 받았다면") {
        val cards = Cards(
            Suit.SPADE to Rank.KING,
            Suit.DIAMOND to Rank.NINE
        )
        val player = Player("원동재", cards)
        When("2다이아몬드를 뽑았을때") {
            player.drawBy(Card(Suit.DIAMOND, Rank.TWO))
            val isHit = player.isHit()
            Then("플레이어 상태는 히트이다.") {
                isHit shouldBe true
            }
        }
    }

    given("K스페이드, A다이아몬드를 받았다면") {
        val cards = Cards(
            Suit.SPADE to Rank.KING,
            Suit.DIAMOND to Rank.ACE
        )
        val player = Player("원동재", cards)
        When("블랙잭 여부를 확인하면") {
            val isBlackJack = player.isBlackJack()
            Then("블랙잭이다.") {
                isBlackJack shouldBe true
            }
        }
    }

    given("플레이어와 딜러가 주어지고 베팅금액이 1000원일때") {
        val player = Player(
            "원동재",
            Cards(
                Suit.SPADE to Rank.KING,
                Suit.DIAMOND to Rank.KING
            ),
            Money(1000)
        )
        val dealer = Dealer(
            Cards(
                Suit.SPADE to Rank.KING,
                Suit.DIAMOND to Rank.KING,
                Suit.HEART to Rank.KING,
            )
        )
        When("딜러가 버스트일때 수익 계산시") {
            player.stand()
            val result = player.winLoseMoney(dealer)
            Then("수익은 1000원이다.") {
                result.amount shouldBe 1000
            }
        }
    }

    given("플레이어와 딜러가 주어지고 베팅금액이 1000원일때") {
        val player = Player(
            "원동재",
            Cards(
                Suit.SPADE to Rank.KING,
                Suit.DIAMOND to Rank.ACE
            ),
            Money(1000)
        )
        val dealer = Dealer(
            Cards(
                Suit.SPADE to Rank.KING,
                Suit.DIAMOND to Rank.KING,
            )
        )
        When("플레이어가 블랙잭일때 수익 계산시") {
            val result = player.winLoseMoney(dealer)
            Then("수익은 1500원이다.") {
                result.amount shouldBe 1500
            }
        }
    }

    given("플레이어와 딜러가 주어지고 베팅금액이 1000원일때") {
        val player = Player(
            "원동재",
            Cards(
                Suit.SPADE to Rank.KING,
                Suit.DIAMOND to Rank.KING
            ),
            Money(1000)
        )
        val dealer = Dealer(
            Cards(
                Suit.SPADE to Rank.KING,
                Suit.DIAMOND to Rank.KING,
            )
        )
        When("플레이어가 버스트일때 수익 계산시") {
            player.drawBy(Card(Suit.DIAMOND, Rank.TWO))
            val result = player.winLoseMoney(dealer)
            Then("수익은 -1000원이다.") {
                result.amount shouldBe -1000
            }
        }
    }
})
