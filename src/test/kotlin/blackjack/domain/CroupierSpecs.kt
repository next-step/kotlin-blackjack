package blackjack.domain

import blackjack.domain.Denomination.ACE
import blackjack.domain.Denomination.EIGHT
import blackjack.domain.Denomination.KING
import blackjack.domain.Denomination.SEVEN
import blackjack.domain.Denomination.SIX
import blackjack.domain.Denomination.TWO
import blackjack.domain.Suit.CLOVER
import blackjack.domain.Suit.DIAMOND
import blackjack.domain.Suit.HEART
import blackjack.domain.Suit.SPADE
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe

class CroupierSpecs : DescribeSpec({

    describe("크루피어는") {
        context("카드를 분배할 플레이어가 있고 분배할 카드가 충분하다면") {
            val deck = CustomDeck(
                listOf(KING to SPADE, ACE to SPADE, SIX to HEART, EIGHT to DIAMOND)
            )
            val dealer = Croupier(deck = deck)
            val players = listOf(NormalPlayer("1") { true }, NormalPlayer("2") { true })
            it("모든 플레이어에게 카드를 2장 분배한다") {
                dealer.distribute(players)
                deck.sizeOfRemaining() shouldBe 0
                players[0].hand shouldBeEqualToComparingFields hand(KING to SPADE, ACE to SPADE)
                players[1].hand shouldBeEqualToComparingFields hand(SIX to HEART, EIGHT to DIAMOND)
            }
        }

        context("카드를 분배할 플레이어가 없다면") {
            val deck = CustomDeck(
                listOf(KING to SPADE, ACE to SPADE, SIX to HEART, EIGHT to DIAMOND)
            )
            val dealer = Croupier(deck)
            val players = emptyList<NormalPlayer>()
            it("카드를 분배할 수 없다") {
                shouldThrowExactly<IllegalArgumentException> {
                    dealer.distribute(players)
                }
            }
        }

        context("분배할 카드가 부족하다면") {
            val emptyDeck = CustomDeck(emptyList())
            val dealer = Croupier(emptyDeck)
            val players = listOf(NormalPlayer("1"), NormalPlayer("2"))
            it("카드를 분배할 수 없다") {
                shouldThrowExactly<IllegalStateException> {
                    dealer.distribute(players)
                }
            }
        }

        context("hit 할 수 있는 플레이어가 hit을 선택했으면") {
            val deck = CustomDeck(
                listOf(TWO to SPADE)
            )
            val dealer = Croupier(deck)
            val player = NormalPlayer("name", hand(SIX to HEART, SEVEN to DIAMOND)) { true }
            it("카드 한 장을 거래한다") {
                dealer.dealWith(player) shouldBe true
                player.hand shouldBeEqualToComparingFields hand(TWO to SPADE)
                player.calculateHand() shouldBe 15
            }
        }

        context("플레이어가 hit 할 수 없다면") {
            val dealer = Croupier()
            val player = NormalPlayer("name", hand(KING to HEART, ACE to DIAMOND)) { true }
            it("카드를 거래하지 않는다") {
                dealer.dealWith(player) shouldBe false
            }
        }

        context("플레이어가 hit을 선택하지 않았으면") {
            val dealer = Croupier()
            val player = NormalPlayer("name") { false }
            it("카드를 거래하지 않는다") {
                dealer.dealWith(player) shouldBe false
            }
        }

        context("거래할 카드가 부족하다면") {
            val deck = CustomDeck(emptyList())
            val dealer = Croupier(deck)
            val player = NormalPlayer("js")
            it("카드를 거래할 수 없다") {
                shouldThrowExactly<IllegalStateException> { dealer.dealWith(player) }
            }
        }

        it("항상 hit을 선택한다") {
            val dealer = Croupier()
            dealer.selectHit() shouldBe true
        }

        it("분배된 카드를 자신의 카드 패에 추가할 수 있다") {
            val pair = DistributedCards(Card(KING, SPADE), Card(ACE, HEART))
            val dealer = Croupier()
            dealer.initialize(pair)
            dealer.hand shouldBeEqualToComparingFields hand(KING to SPADE, ACE to HEART)
        }

        context("카드 패의 점수가 16보다 같거나 낮다면") {
            val hand = hand(TWO to CLOVER, ACE to SPADE)
            val dealer = Croupier(player = NormalPlayer("딜러", hand))
            it("카드 1장을 자신의 카드 패에 추가할 수 있다") {
                dealer.hit(Card(KING, HEART))
                dealer.hand shouldBeEqualToComparingFields hand(TWO to CLOVER, ACE to SPADE, KING to HEART)
            }
        }

        context("카드 패의 점수가 16보다 높다면") {
            val hand = hand(SIX to CLOVER, ACE to SPADE)
            val dealer = Croupier(player = NormalPlayer("딜러", hand))
            it("자신의 카드 패에 새로운 카드를 추가할 수 없다") {
                shouldThrowExactly<IllegalStateException> { dealer.hit(Card(KING, HEART)) }
            }
        }
    }
})
