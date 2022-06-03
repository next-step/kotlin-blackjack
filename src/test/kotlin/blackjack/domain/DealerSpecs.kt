package blackjack.domain

import blackjack.domain.Denomination.ACE
import blackjack.domain.Denomination.EIGHT
import blackjack.domain.Denomination.KING
import blackjack.domain.Denomination.SIX
import blackjack.domain.Suit.DIAMOND
import blackjack.domain.Suit.HEART
import blackjack.domain.Suit.SPADE
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe

class DealerSpecs : DescribeSpec({

    describe("딜러는") {
        context("카드를 분배할 플레이어가 있고 분배할 카드가 충분하다면") {
            val deck = CustomDeck(
                cards(KING to SPADE, ACE to SPADE, SIX to HEART, EIGHT to DIAMOND)
            )
            val dealer = Dealer(deck)
            val players = listOf(Player("1"), Player("2"))
            it("모든 플레이어에게 카드를 2장 분배한다") {
                dealer.distribute(players)
                deck.sizeOfRemaining() shouldBe 0
                players[0].hand shouldBeEqualToComparingFields hand(KING to SPADE, ACE to SPADE)
                players[1].hand shouldBeEqualToComparingFields hand(SIX to HEART, EIGHT to DIAMOND)
            }
        }

        context("카드를 분배할 플레이어가 없다면") {
            val deck = CustomDeck(
                cards(KING to SPADE, ACE to SPADE, SIX to HEART, EIGHT to DIAMOND)
            )
            val dealer = Dealer(deck)
            val players = emptyList<Player>()
            it("카드를 분배할 수 없다") {
                shouldThrowExactly<IllegalArgumentException> {
                    dealer.distribute(players)
                }
            }
        }

        context("분배할 카드가 부족하다면") {
            val emptyDeck = CustomDeck(emptyList())
            val dealer = Dealer(emptyDeck)
            val players = listOf(Player("1"), Player("2"))
            it("카드를 분배할 수 없다") {
                shouldThrowExactly<IllegalStateException> {
                    dealer.distribute(players)
                }
            }
        }

        context("카드를 거래할 플레이어가 있다면") {
            val deck = CustomDeck(
                cards(KING to SPADE, ACE to SPADE)
            )
            val dealer = Dealer(deck)
            val player = Player("js")
            it("해당 플레이어와 카드를 거래한다") {
                dealer.deal(player)
                deck.sizeOfRemaining() shouldBe 1
                player.hand shouldBeEqualToComparingFields hand(KING to SPADE)
            }
        }
        context("거래할 카드가 부족하다면") {
            val deck = CustomDeck(emptyList())
            val dealer = Dealer(deck)
            val player = Player("js")
            it("카드를 거래할 수 없다") {
                shouldThrowExactly<IllegalStateException> { dealer.deal(player) }
            }
        }
    }
})
