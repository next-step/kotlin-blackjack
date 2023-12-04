package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GameRewardTest : BehaviorSpec({
    given("플레이어가 블랙잭인 경우") {
        val player = Player(Nickname("플레이어"), Amount(1000.0)).apply {
            receiveCard(Card(Symbol.SPADE, Rank.ACE))
            receiveCard(Card(Symbol.SPADE, Rank.TEN))
        }

        `when`("게임에서 승리하면") {
            val reward = GameReward.create(Amount(1000.0), GameOutcome.WIN, player)

            then("1.5 배율이 적용된다.") {
                reward.getValue() shouldBe 1500.0
            }
        }

        `when`("게임에서 패배하면") {
            val reward = GameReward.create(Amount(1000.0), GameOutcome.LOSE, player)

            then("-1.5 배율이 적용된다.") {
                reward.getValue() shouldBe -1500.0
            }
        }
    }

    given("플레이어가 블랙잭이 아닌 경우") {
        val player = Player(Nickname("플레이어"), Amount(1000.0)).apply {
            receiveCard(Card(Symbol.SPADE, Rank.TWO))
            receiveCard(Card(Symbol.SPADE, Rank.ACE))
        }

        `when`("게임에서 승리하면") {
            val reward = GameReward.create(Amount(1000.0), GameOutcome.WIN, player)

            then("1.0 배율이 적용된다.") {
                reward.getValue() shouldBe 1000.0
            }
        }

        `when`("게임에서 패배하면") {
            val reward = GameReward.create(Amount(1000.0), GameOutcome.LOSE, player)

            then("-1.0 배율이 적용된다.") {
                reward.getValue() shouldBe -1000.0
            }
        }
    }
})
