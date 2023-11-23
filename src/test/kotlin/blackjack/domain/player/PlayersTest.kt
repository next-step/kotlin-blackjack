package blackjack.domain.player

import blackjack.domain.Action
import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PlayersTest : DescribeSpec({
    describe("플레이어들 생성") {
        context("플레이어 이름이 주어지면") {
            val name1 = PlayerName("홍길동")
            val name2 = PlayerName("베트맨")
            val names = PlayerNames(listOf(name1, name2))

            val result = Players.of(names) { Action.HIT }
            it("주어진 이름 순서대로 플레이어들 생성") {
                result.all[0] shouldBe Player(name1, { Action.HIT })
                result.all[1] shouldBe Player(name2, { Action.HIT })
            }
            it("첫 이름의 플레이어가 첫 순번") {
                result.inTurn shouldBe Player(name1, { Action.HIT })
            }
        }

        context("플레이어가 2명이 아닌 경우") {
            val playerNames = PlayerNames(listOf(PlayerName("홍길동"), PlayerName("베트맨"), PlayerName("아이언맨")))
            it("플레이어 생성 실패") {
                shouldThrowExactly<IllegalArgumentException> {
                    Players.of(playerNames) { Action.HIT }
                }
            }
        }
    }

    describe("현재 플레이어가 최대 점수를 넘었는지 여부 반환") {
        context("현재 플레이어가 최대 점수를 넘었을 경우") {
            val playerOverMaxScore = Player(
                PlayerName("kim"), { Action.HIT }, Hand(
                    mutableListOf(
                        Card(Suit.DIAMOND, Rank.THREE), Card(Suit.HEART, Rank.TEN), Card(Suit.HEART, Rank.TEN)
                    )
                )
            )
            val players = Players(listOf(playerOverMaxScore, Player(PlayerName("kim"), { Action.HIT }, Hand())))
            it("true 반환") {
                players.isPlayerInTurnOverMaxScore shouldBe true
            }
        }

        context("현재 플레이어가 최대 점수를 넘지 않았을 경우") {
            val playerUnderMaxScore = Player(
                PlayerName("kim"), { Action.HIT }, Hand(
                    mutableListOf(
                        Card(Suit.DIAMOND, Rank.ACE), Card(Suit.HEART, Rank.TEN)
                    )
                )
            )
            val players = Players(listOf(playerUnderMaxScore, Player(PlayerName("kim"), { Action.HIT }, Hand())))
            it("false 반환") {
                players.isPlayerInTurnOverMaxScore shouldBe false
            }
        }
    }

    describe("다음 플레이어에게 차례 넘김") {
        val playerList = listOf(
            Player(PlayerName("hong"), { Action.HIT }, Hand()),
            Player(PlayerName("dong"), { Action.HIT }, Hand()),
        )
        val players = Players(playerList)

        context("플레이어 1이 차례인 경우") {
            players.inTurn shouldBe playerList.first()
            players.changePlayer()
            it("플레이어 2에게 차례가 넘어감") {
                players.inTurn shouldBe playerList.last()
            }
        }

        context("플레이어 2가 차례인 경우") {
            players.inTurn shouldBe playerList.last()

            it("턴이 끝났다는 에러") {
                shouldThrowExactly<IllegalArgumentException> {
                    players.changePlayer()
                }
            }
        }
    }

    describe("마지막 플레이어 차례인지 조회") {
        val playerList = listOf(
            Player(PlayerName("hong"), { Action.HIT }, Hand()),
            Player(PlayerName("dong"), { Action.HIT }, Hand()),
        )
        val players = Players(playerList)

        context("플레이어 1이 차례인 경우") {
            players.inTurn shouldBe playerList.first()
            it("false 반환") {
                players.isLastTurn shouldBe false
            }
        }

        context("플레이어 2가 차례인 경우") {
            players.changePlayer()
            players.inTurn shouldBe playerList.last()

            it("true 반환") {
                players.isLastTurn shouldBe true
            }
        }
    }
})
