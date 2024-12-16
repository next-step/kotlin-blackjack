package domain

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.domain.Suit
import fixture.CardListFixture
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
                    Card(Suit.HEARTS, CardNumber.FOUR),
                )
            deck = Deck(cardList)
            player = Player("name")
            sut = Dealer(deck)
        }

        it("덱에서 카드를 한장 뽑는다.") {
            sut.hit(player)
            player.ownedCards shouldContainExactly
                listOf(
                    Card(Suit.CLUBS, CardNumber.ACE),
                )
        }
    }

    describe("deal test") {
        lateinit var pobi: Player
        lateinit var crong: Player
        lateinit var participants: Participants
        lateinit var cardList: MutableList<Card>

        lateinit var sut: Dealer

        beforeTest {
            pobi = Player(name = "pobi")
            crong = Player(name = "crong")
            participants =
                Participants(
                    listOf(
                        pobi, crong,
                    ),
                )
            cardList = CardListFixture.simpleCardList()

            val deck = Deck(cardList)

            sut = Dealer(deck)
        }

        it("플레이어들에게 카드를 2장씩 나누어준다.") {
            sut.deal(participants)
            pobi.ownedCards.size shouldBe 2
            crong.ownedCards.size shouldBe 2
        }

        it("카드는 차례대로 한장씩 나두어 준다.") {
            sut.deal(participants)

            pobi.ownedCards[0] shouldBe CardListFixture.simpleCardList()[0]
            crong.ownedCards[0] shouldBe CardListFixture.simpleCardList()[1]
            pobi.ownedCards[1] shouldBe CardListFixture.simpleCardList()[2]
            crong.ownedCards[1] shouldBe CardListFixture.simpleCardList()[3]
        }
    }
})
