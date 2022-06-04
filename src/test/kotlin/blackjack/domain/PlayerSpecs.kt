package blackjack.domain

import blackjack.domain.Denomination.ACE
import blackjack.domain.Denomination.KING
import blackjack.domain.Denomination.TEN
import blackjack.domain.Suit.CLOVER
import blackjack.domain.Suit.HEART
import blackjack.domain.Suit.SPADE
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe

class PlayerSpecs : DescribeSpec({

    describe("플레이어는") {
        it("카드 패를 가질 수 있다") {
            val hand = hand(KING to CLOVER, ACE to SPADE)
            shouldNotThrowAny { Player("js", hand) { true } }
        }

        it("카드 2장을 자신의 카드 패에 추가할 수 있다") {
            val player = Player("name") { true }
            val card1 = Card.from(KING, CLOVER)
            val card2 = Card.from(ACE, SPADE)
            player.initialize(card1 to card2)
            player.hand shouldBeEqualToComparingFields Hand(listOf(card1, card2))
        }

        it("hit을 선택할 수 있다") {
            io.kotest.data.forAll(
                table(
                    headers("플레이어", "hit 선택 여부"),
                    row(Player("name") { true }, true),
                    row(Player("name") { false }, false),
                )
            ) { player, result ->
                player.selectHit() shouldBe result
            }
        }

        it("카드 1장을 자신의 카드 패에 추가할 수 있다") {
            val player = Player("name") { true }
            val card = Card.from(KING, CLOVER)
            player.hit(card)
            player.hand shouldBeEqualToComparingFields Hand(listOf(card))
        }

        it("자신이 지닌 카드 패의 점수를 계산할 수 있다") {
            val player = Player("name", hand(KING to CLOVER, ACE to SPADE)) { true }
            player.calculateHand() shouldBe 21
        }

        context("자신이 지닌 카드 패의 점수가 21 이상이면") {
            val player = Player("name", hand(KING to CLOVER, ACE to SPADE)) { true }
            it("자신의 카드 패에 새로운 카드를 추가할 수 없다") {
                shouldThrowExactly<IllegalStateException> {
                    player.hit(Card.from(TEN, HEART))
                }
            }
        }
    }
})
