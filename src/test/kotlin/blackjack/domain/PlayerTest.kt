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
            val player = Player(name, trumpCard.firstCardDraw())
            then("플레이어의 카드는 2장이 된다.") {
                player.cards.cards.size shouldBe 2
            }
        }
    }

    given("트럼프 카드가 주어지고 플레이어가 2장을 받았다면") {
        val trumpCard = TrumpCard.init()
        val player = Player("원동재", trumpCard.firstCardDraw())
        `when`("플레이어 카드에 카드를 한장 더 뽑으면") {
            player.drawBy(trumpCard)
            then("플레이어 카드는 3장이다.") {
                player.cards.cards.size shouldBe 3
            }
        }
    }

    given("카드 A스페이드,A다이아몬드, K스페이드, K다이아몬드를 받았다면") {
        val cards = Cards(
            Suit.SPADE to Rank.ACE,
            Suit.DIAMOND to Rank.ACE,
            Suit.SPADE to Rank.KING,
            Suit.DIAMOND to Rank.KING
        )
        val player = Player("원동재", cards)
        When("버스트 여부를 확인할 때") {
            val isBurst = player.isBurst()
            Then("버스트이다.") {
                isBurst shouldBe true
            }
        }
    }

    given("카드 A다이아몬드, K스페이드, K다이아몬드를 받았다면") {
        val cards = Cards(
            Suit.DIAMOND to Rank.ACE,
            Suit.SPADE to Rank.KING,
            Suit.DIAMOND to Rank.KING
        )
        val player = Player("원동재", cards)
        When("버스트 여부를 확인할 때") {
            val isBurst = player.isBurst()
            Then("버스트가 아니다.") {
                isBurst shouldBe false
            }
        }
    }

    given("카드 A스페이드,A다이아몬드, K스페이드, K다이아몬드를 받았다면") {
        val cards = Cards(
            Suit.SPADE to Rank.ACE,
            Suit.DIAMOND to Rank.ACE,
            Suit.SPADE to Rank.KING,
            Suit.DIAMOND to Rank.KING
        )
        val player = Player("원동재", cards)
        When("히트 여부를 확인할 때") {
            val isHit = player.isHit()
            Then("히트가 아니다.") {
                isHit shouldBe false
            }
        }
    }

    given("K스페이드, K다이아몬드를 받았다면") {
        val cards = Cards(
            Suit.SPADE to Rank.KING,
            Suit.DIAMOND to Rank.KING
        )
        val player = Player("원동재", cards)
        When("히트 여부를 확인할 때") {
            val isHit = player.isHit()
            Then("히트이다.") {
                isHit shouldBe true
            }
        }
    }
})
