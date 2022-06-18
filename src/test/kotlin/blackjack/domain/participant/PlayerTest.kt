package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination.FIVE
import blackjack.domain.card.Denomination.FOUR
import blackjack.domain.card.Denomination.KING
import blackjack.domain.card.Denomination.SIX
import blackjack.domain.card.Point
import blackjack.domain.card.Suit.DIAMOND
import blackjack.domain.card.Suit.HEART
import blackjack.domain.card.Suit.SPADE
import blackjack.domain.hand
import blackjack.domain.to
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe

class PlayerTest : DescribeSpec({

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
            it("hit을 선택하면 블랙잭을 계속 진행할 수 있다") {
                player.isPlayable { true } shouldBe true
            }
            it("hit을 선택하지 않으면 블랙잭을 계속 진행할 수 없다") {
                player.isPlayable { false } shouldBe false
                player.state shouldBe Stay(player.point())
            }
        }

        context("Hittable 상태가 아니라면") {
            val states = listOf(Bust, BlackJack, Stay(Point(10)))
            it("카드를 받을 수 없다") {
                states.forAll {
                    val player = Player("이름", state = it)
                    val card = Card(KING, SPADE)
                    shouldThrowExactly<IllegalStateException> {
                        player.receive(card)
                    }
                }
            }
            it("블랙잭을 더 이상 진행할 수 없다") {
                states.forAll {
                    val player = Player("이름", state = it)
                    player.isPlayable { true } shouldBe false
                }
            }
        }

        context("hit을 선택하지 않으면") {
            val player = Player("이름")
            it("`Stay` 상태가 된다") {
                player.isPlayable { false }
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

        context("딜러에게 이기면") {
            it("베팅한 금액을 받는다") {
                val bettingMoney = Money(1000)
                val player = Player("name", state = BlackJack, bettingMoney = bettingMoney)
                val dealer = Dealer(state = Bust)
                player.match(dealer) shouldBe Match.WIN
                player.profit shouldBe Money(1000)
                dealer.profit shouldBe Money(-1000)
            }
        }

        context("딜러에게 진다면") {
            it("베팅한 금액을 잃는다") {
                val bettingMoney = Money(1000)
                val player = Player("name", state = Bust, bettingMoney = bettingMoney)
                val dealer = Dealer(state = BlackJack)
                player.match(dealer) shouldBe Match.LOSE
                player.profit shouldBe Money(-1000)
                dealer.profit shouldBe Money(1000)
            }
        }
    }
})
