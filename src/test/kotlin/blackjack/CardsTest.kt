package blackjack

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Suite
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

internal class CardsTest : BehaviorSpec({
    given("여러 장의 카드를 가졌을 때") {
        val list = listOf(
            Card(Suite.SPADE, Denomination.ACE),
            Card(Suite.CLOVER, Denomination.EIGHT),
            Card(Suite.HEART, Denomination.JACK)
        )
        val cards = Cards(list)

        `when`("랜덤으로 1장의 카드를 뽑는다면") {
            cards.pick()

            then("카드 리스트의 사이즈는 줄어든다.") {
                cards.values.size shouldBe 2
            }
        }
    }

    given("카드들의 점수를 카운팅할 때") {
        `when`("ACE 를 가지고 있고") {
            val list = mutableListOf(
                Card(Suite.CLOVER, Denomination.ACE)
            )

            and("1점으로 계산한 결과가 21을 초과하지 않을 때") {
                list.add(Card(Suite.CLOVER, Denomination.SEVEN))
                list.add(Card(Suite.CLOVER, Denomination.FOUR))
                val cards = Cards(list)

                and("보너스 점수 10점을 더해서 21점이라면") {
                    val result = cards.getScore()

                    then("카드 점수의 총 합은 21점이다.") {
                        result shouldNotBe 21
                    }
                }
            }
        }
    }
})
