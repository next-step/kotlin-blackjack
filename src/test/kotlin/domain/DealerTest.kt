package domain

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.Suit
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class DealerTest : DescribeSpec({
    describe("hit test") {
        lateinit var cardList: MutableList<Card>
        lateinit var deck: Deck
        lateinit var player: Player
        lateinit var sut: Dealer
        beforeTest {
            cardList =
                mutableListOf(
                    Card(Suit.CLUBS, CardNumber.ACE),
                    Card(Suit.DIAMONDS, CardNumber.TWO),
                    Card(Suit.HEARTS, CardNumber.THREE),
                )
            deck = Deck(cardList)
            player = Player("name")
            sut = Dealer(deck)
        }

        it("플레이어에게 덱에서 뽑은 첫 번째 카드를 전달한다") {
            sut.hit(player)

            player.ownedCards.size shouldBe 1
            player.ownedCards shouldContainExactly
                listOf(
                    Card(Suit.CLUBS, CardNumber.ACE),
                )
        }
    }
})
