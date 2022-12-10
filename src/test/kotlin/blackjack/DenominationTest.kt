package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

internal class DenominationTest : BehaviorSpec({
    given("블랙잭 게임에서") {
        `when`("플레잉 카드를 사용할 때") {
            then("13개의 끗수가 존재한다.") {
                Denomination.values().size shouldBe 13
            }
        }
    }

    given("카드들의 점수를 카운팅할 때") {
        `when`("ACE 를 가지고 있을 때") {
            val list = mutableListOf(
                Denomination.ACE
            )

            and("11점으로 계산해서") {
                and("21점이 넘지 않는다면") {
                    list.add(Denomination.TWO)
                    list.add(Denomination.FOUR)

                    val result = Denomination.sum(list)

                    then("11점으로 계산한다.") {
                        result shouldNotBe 7
                        result shouldBe 17
                    }
                }

                and("21점이 넘는다면") {
                    list.add(Denomination.SEVEN)

                    val result = Denomination.sum(list)

                    then("1점으로 계산한다.") {
                        result shouldNotBe 24
                        result shouldBe 14
                    }
                }
            }
        }
    }
})
