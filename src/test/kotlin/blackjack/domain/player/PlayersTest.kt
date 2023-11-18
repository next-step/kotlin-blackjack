package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PlayersTest : DescribeSpec({
    describe("플레이어들 생성") {
        context("플레이어 이름이 주어지면") {
            val name1 = PlayerName("홍길동")
            val name2 = PlayerName("베트맨")
            val names = PlayerNames(listOf(name1, name2))

            val result = Players.from(names)
            it("주어진 이름 순서대로 플레이어들 생성") {
                result.allPlayers[0] shouldBe Player(name1)
                result.allPlayers[1] shouldBe Player(name2)
            }
            it("첫 이름의 플레이어가 첫 순번") {
                result.playerInTurn shouldBe Player(name1)
            }
        }
    }

    describe("현재 플레이어가 최대 점수를 넘었는지 여부 반환") {
        context("현재 플레이어가 최대 점수를 넘었을 경우") {
            val playerOverMaxScore = Player(
                PlayerName("kim"), Hand(
                    mutableListOf(
                        Card(Suit.DIAMOND, Rank.THREE), Card(Suit.HEART, Rank.TEN), Card(Suit.HEART, Rank.TEN)
                    )
                )
            )
            val players = Players(listOf(playerOverMaxScore, Player(PlayerName("kim"), Hand())))
            it("true 반환") {
                players.isPlayerInTurnOverMaxScore shouldBe true
            }
        }

        context("현재 플레이어가 최대 점수를 넘지 않았을 경우") {
            val playerUnderMaxScore = Player(
                PlayerName("kim"), Hand(
                    mutableListOf(
                        Card(Suit.DIAMOND, Rank.ACE), Card(Suit.HEART, Rank.TEN)
                    )
                )
            )
            val players = Players(listOf(playerUnderMaxScore, Player(PlayerName("kim"), Hand())))
            it("false 반환") {
                players.isPlayerInTurnOverMaxScore shouldBe false
            }
        }
    }
})
