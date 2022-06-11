package blackjack.domain

import blackjack.domain.Denomination.KING
import blackjack.domain.State.HITTABLE
import blackjack.domain.Suit.SPADE
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe

class PlayerSpecs : DescribeSpec({

    describe("플레이어는") {
        it("최초 상태로 Hittable 상태를 가진다") {
            val player = Player("이름")
            player.state shouldBe HITTABLE
        }
        context("Hittable 상태라면") {
            val player = Player("이름", state = HITTABLE)
            it("카드를 받을 수 있다") {
                val card = Card(KING, SPADE)
                player.receive(card)
                player.hand shouldBeEqualToComparingFields hand(card)
            }
        }

        context("Hittable 이 아닌 상태라면") {
            it("카드를 받을 수 없다") {
                State.values()
                    .filterNot { it == HITTABLE }
                    .forAll {
                        val player = Player("이름", state = it)
                        val card = Card(KING, SPADE)
                        shouldThrowExactly<IllegalStateException> {
                            player.receive(card)
                        }
                    }
            }
        }
    }
})
