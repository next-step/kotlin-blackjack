package blackjack.domain.player

import blackjack.domain.Action
import blackjack.mock.player
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PlayersTest : DescribeSpec({
    describe("Players.of()") {
        context("플레이어 이름이 주어지면") {
            val name1 = PlayerName("홍길동")
            val name2 = PlayerName("베트맨")
            val names = PlayerNames(listOf(name1, name2))

            val result = Players.of(names) { Action.HIT }
            it("주어진 이름 순서대로 플레이어들이 생성된다") {
                result.value[0].name shouldBe name1
                result.value[1].name shouldBe name2
            }
            it("첫 이름의 플레이어가 첫 순번이다") {
                result.inTurn.name shouldBe name1
            }
        }

        context("플레이어가 2명이 아닌 경우") {
            val playerNames = PlayerNames(listOf(PlayerName("홍길동"), PlayerName("베트맨"), PlayerName("아이언맨")))
            it("플레이어 생성에 실패한다") {
                shouldThrowExactly<IllegalArgumentException> {
                    Players.of(playerNames) { Action.HIT }
                }
            }
        }
    }

    describe("changePlayer()") {
        val playerList = listOf(player("kim"), player("lee"))
        val players = Players(playerList)

        context("플레이어 1이 차례인 경우") {
            players.inTurn shouldBe playerList.first()
            players.changePlayer()
            it("플레이어 2에게 차례가 넘어간다") {
                players.inTurn shouldBe playerList.last()
            }
        }

        context("플레이어 2가 차례인 경우") {
            players.inTurn shouldBe playerList.last()

            it("턴이 끝났다는 에러가 발생한다") {
                shouldThrowExactly<IllegalArgumentException> {
                    players.changePlayer()
                }
            }
        }
    }

    describe("isLastTurn") {
        val playerList = listOf(player("kim"), player("lee"))
        val players = Players(playerList)

        context("플레이어 1이 차례인 경우") {
            players.inTurn shouldBe playerList.first()
            it("false가 반환된다") {
                players.isLastTurn shouldBe false
            }
        }

        context("플레이어 2가 차례인 경우") {
            players.changePlayer()
            players.inTurn shouldBe playerList.last()

            it("true가 반환된다") {
                players.isLastTurn shouldBe true
            }
        }
    }
})
