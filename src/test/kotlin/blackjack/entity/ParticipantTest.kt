package blackjack.entity

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe

class ParticipantTest : DescribeSpec({
    describe("플레이어는") {

        context("초기화할 때") {
            it("이름을 설정할 수 있어야 한다") {
                val player = Player("Alice", BettingAmount(1000))
                player.name shouldBe "Alice"
            }

            it("빈 손패를 기본 값으로 가져야 한다") {
                val player = Player("Bob", BettingAmount(1000))
                player.hand.cards.shouldBeEmpty()
            }
        }

        context("카드를 추가할 때") {
            val player = Player("Charlie", BettingAmount(1000))
            val spadesTen = Card(Suit.SPADES, Rank.TEN)
            val heartsAce = Card(Suit.HEARTS, Rank.ACE)

            it("손패에 카드가 추가되어야 한다") {
                player.receiveCard(spadesTen)
                player.hand.cards.size shouldBe 1
                player.hand.cards[0] shouldBe spadesTen
            }

            it("추가한 카드가 손패 리스트에 포함되어야 한다") {
                player.receiveCard(heartsAce)
                player.hand.cards shouldBe listOf(spadesTen, heartsAce)
            }
        }
        context("점수를 계산할 때") {
            val player = Player("Charlie", BettingAmount(1000))
            val spadesTen = Card(Suit.SPADES, Rank.TEN)
            val heartsAce = Card(Suit.HEARTS, Rank.ACE)
            player.receiveCard(spadesTen)
            player.receiveCard(heartsAce)

            it("현재 손패의 점수를 계산할 수 있어야 한다") {
                player.calculateScore() shouldBe 21
            }
        }
        context("버스트 상태를 확인할 때") {
            val player = Player("Charlie", BettingAmount(1000))
            val spadesKing = Card(Suit.SPADES, Rank.KING)
            val heartsKing = Card(Suit.HEARTS, Rank.KING)
            val clubsTwo = Card(Suit.CLUBS, Rank.TWO)
            player.receiveCard(spadesKing)
            player.receiveCard(heartsKing)
            player.receiveCard(clubsTwo)

            it("버스트 상태를 반환해야 한다") {
                player.isBusted() shouldBe true
            }
        }
        context("블랙잭 상태를 확인할 때") {
            val player = Player("Charlie", BettingAmount(1000))
            val spadesKing = Card(Suit.SPADES, Rank.KING)
            val diamondsAce = Card(Suit.DIAMONDS, Rank.ACE)
            player.receiveCard(spadesKing)
            player.receiveCard(diamondsAce)

            it("블랙잭 상태를 반환해야 한다") {
                player.isBlackjack() shouldBe true
            }
        }
    }
})
