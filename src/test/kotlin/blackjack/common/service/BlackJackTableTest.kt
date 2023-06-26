package blackjack.common.service

import blackjack.scorerule.domain.ScorePlayer
import blackjack.scorerule.domain.BlackJackScoreTable
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class BlackJackTableTest : BehaviorSpec({
    given("게임을 시작할 때 모든 플레이어에게 2장을 나누어준다.") {
        val players = arrayOf(ScorePlayer("test"), ScorePlayer("test2"))
        val table = BlackJackScoreTable(players)
        table.beginRound()
        `when`("테이블에서 초기 값을 설정한다.") {
            then("플레이어들은 2장을 갖고 있는다.") {
                players.forEach {
                    it.hands().size shouldBe 2
                }
            }
        }

        `when`("테이블에서 플레이어의 이름을 가져온다.") {
            then("플레이어 이름은 ', '로 구분한다.") {
                table.getPlayersName() shouldBe "test, test2"
            }
        }

        `when`("테이블에서 딜러와 플레이어의 상태를 가져온다.") {
            val allStatusWithDealer = table.getAllStatusWithDealer()

            then("플레이어 이름은 ', '로 구분한다.") {
                allStatusWithDealer.size shouldBe 3
                allStatusWithDealer.forEach {
                    it.handRepresent.shouldNotBeNull()
                    it.optimalValue shouldBeGreaterThan 0
                }
                val names = allStatusWithDealer.map { it.name }
                names shouldContainExactlyInAnyOrder listOf("딜러", "test", "test2")
            }
        }

        `when`("플레이어의 턴을 진행할 때에 원하는 만큼 계속 카드를 뽑는다.") {
            then("21 넘으면 더이상 뽑을 수 없음 에러 발생") {
                shouldThrow<IllegalStateException> {
                    table.executePlayerTurns(
                        players,
                        wantToHit = { true },
                        handNotice = { }
                    )
                }.message shouldBe "더이상 카드를 받을 수 없습니다."
            }
        }

        `when`("딜러의 턴에 딜러의 값이 16보다 낮으면 카드를 뽑아야 한다.") {
            val originOptimalValue = table.getDealerStatus().optimalValue
            table.executeDealerTurn { }
            val newOptimalValue = table.getDealerStatus().optimalValue
            then("새로 뽑았다면 이전 값과 같고 아니면 다르다.") {
                if (originOptimalValue <= 16) {
                    newOptimalValue shouldBeGreaterThan originOptimalValue
                } else {
                    newOptimalValue shouldBe originOptimalValue
                }
            }
        }
    }
})
