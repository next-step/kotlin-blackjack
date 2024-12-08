package blackjack.domain

import blackjack.domain.ResultStatistics.Constant.LOSE_STATISTICS
import blackjack.domain.ResultStatistics.Constant.WIN_STATISTICS
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertAll

class PlayerTest : BehaviorSpec({
    Given("`Name`을 가진다") {
        val name = "철수"

        When("이름이 `$name` 일때") {
            val player = Player.from(name)

            Then("이름은 `$name` 이어야 한다") {
                player.name shouldBe name
            }
        }
    }

    Given("`Card`를 받을 수 있다") {
        val player = Player.from("철수")
        val cards =
            createDeck {
                CardRank.ACE to Suit.SPADE
            }

        When("카드를 받을 때") {
            player.receive(cards)

            Then("카드가 추가되어야 한다") {
                player.totalCards shouldBe cards
            }
        }
    }

    Given("`Bust` 상태를 알 수 있다") {
        val player = Player.from("철수")
        val cards =
            createDeck {
                CardRank.ACE to Suit.SPADE
                CardRank.ACE to Suit.SPADE
                CardRank.KING to Suit.SPADE
            }

        When(
            "카드가 ${
                cards.values().joinToString { "${it.rank} ${it.suit}" }
            } 일때 카드 숫자를 합쳐 21을 초과하는지 알 수 있다",
        ) {
            player.receive(cards)

            Then("Bust 상태여야 한다") {
                player.isBust shouldBe true
            }
        }
    }

    Given("플레이어는 통계를 계산한다") {
        val player = Player.from("철수")
        val other = Player.from("영희")
        val cards =
            createDeck {
                CardRank.ACE to Suit.SPADE
                CardRank.KING to Suit.SPADE
            }
        val otherCards =
            createDeck {
                CardRank.ACE to Suit.SPADE
                CardRank.TWO to Suit.SPADE
            }

        player.receive(cards)
        other.receive(otherCards)

        assertAll(
            { player.matchToStatistics(other) shouldBe WIN_STATISTICS },
            { other.matchToStatistics(player) shouldBe LOSE_STATISTICS },
        )
    }
})
