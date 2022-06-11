package blackjack.domain

import blackjack.domain.Denomination.FIVE
import blackjack.domain.Denomination.KING
import blackjack.domain.Denomination.SEVEN
import blackjack.domain.Denomination.SIX
import blackjack.domain.Denomination.TWO
import blackjack.domain.Suit.DIAMOND
import blackjack.domain.Suit.HEART
import blackjack.domain.Suit.SPADE
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe

class DealerSpecs : DescribeSpec({

    describe("딜러는") {
        it("카드 덱으로부터 카드를 한 장 뽑을 수 있다.") {
            val deck: Deck = CustomDeck(cards(KING to SPADE))
            val dealer = Dealer(deck = deck)
            dealer.draw() shouldBe Card(KING, SPADE)
        }
        context("카드 덱에 카드가 존재하지 않으면") {
            it("카드 덱으로부터 카드를 뽑을 수 없다.") {
                val deck: Deck = CustomDeck(emptyList())
                val dealer = Dealer(deck = deck)
                shouldThrowExactly<IllegalStateException> {
                    dealer.draw()
                }
            }
        }

        context("카드를 받고 나서") {
            it("카드 패의 점수가 21점이면 `BlackJack` 상태가 된다") {
                val hand = hand(KING to SPADE, SIX to DIAMOND)
                val dealer = Dealer(hand = hand)
                dealer.receive(Card(FIVE, HEART))
                dealer.state shouldBe BlackJack
            }
            it("카드 패의 점수가 21을 초과하면 `Bust` 상태가 된다") {
                val hand = hand(KING to SPADE, SIX to DIAMOND)
                val dealer = Dealer(hand = hand)
                dealer.receive(Card(SEVEN, HEART))
                dealer.state shouldBe Bust
            }
            it("카드 패의 점수가 17 이상이면 `Stay` 상태가 된다.") {
                val hand = hand(KING to SPADE, SIX to DIAMOND)
                val dealer = Dealer(hand = hand)
                dealer.receive(Card(TWO, HEART))
                dealer.state shouldBe Stay(dealer.point())
            }
        }

        it("카드 패를 공개할 때 첫번째 카드만 공개한다") {
            val hand = hand(KING to SPADE, SIX to DIAMOND)
            val dealer = Dealer(hand = hand)
            dealer.open() shouldBeEqualToComparingFields hand(KING to SPADE)
        }
    }
})
