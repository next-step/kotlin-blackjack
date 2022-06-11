package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination.FIVE
import blackjack.domain.card.Denomination.FOUR
import blackjack.domain.card.Denomination.KING
import blackjack.domain.card.Denomination.SIX
import blackjack.domain.card.Point
import blackjack.domain.card.Suit.DIAMOND
import blackjack.domain.card.Suit.HEART
import blackjack.domain.card.Suit.SPADE
import blackjack.domain.participant.BlackJack
import blackjack.domain.participant.Bust
import blackjack.domain.participant.Hittable
import blackjack.domain.participant.Player
import blackjack.domain.participant.Stay
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe

class PlayerSpecs : DescribeSpec({

    describe("플레이어는") {
        it("최초 상태로 Hittable 상태를 가진다") {
            val player = Player("이름")
            player.state shouldBe Hittable
        }
        context("Hittable 상태라면") {
            val player = Player("이름", state = Hittable)
            it("카드를 받을 수 있다") {
                val card = Card(KING, SPADE)
                player.receive(card)
                player.hand shouldBeEqualToComparingFields hand(card)
            }
        }

        context("Hittable 이 아닌 상태라면") {
            it("카드를 받을 수 없다") {
                listOf(Bust, BlackJack, Stay(Point(10)))
                    .forAll {
                        val player = Player("이름", state = it)
                        val card = Card(KING, SPADE)
                        shouldThrowExactly<IllegalStateException> {
                            player.receive(card)
                        }
                    }
            }
        }

        context("hit을 선택하지 않으면") {
            val player = Player("이름") { false }
            it("`Stay` 상태가 된다") {
                player.saidHit()
                player.state shouldBe Stay(player.point())
            }
        }

        context("카드를 받고 나서") {
            it("카드 패의 점수가 21점이면 `BlackJack` 상태가 된다") {
                val hand = hand(KING to HEART, SIX to DIAMOND)
                val player = Player("name", hand = hand)
                player.receive(Card(FIVE, SPADE))
                player.state shouldBe BlackJack
            }
            it("카드 패의 점수가 21점을 초과하면 `Bust` 상태가 된다") {
                val hand = hand(KING to HEART, SIX to DIAMOND)
                val player = Player("name", hand = hand)
                player.receive(Card(SIX, SPADE))
                player.state shouldBe Bust
            }
            it("카드 패의 점수가 21점 미만이면 현재 상태를 유지한다") {
                val hand = hand(KING to HEART, SIX to DIAMOND)
                val player = Player("name", hand = hand)
                player.receive(Card(FOUR, SPADE))
                player.state shouldBe Hittable
            }
        }
    }
})
