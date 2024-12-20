package domain

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Participant.Player
import blackjack.domain.Suit
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PlayerTest : DescribeSpec({
    describe("player init test") {
        it("사용자의 이름을 받아 객체를 생성한다.") {
            val inputUsername = "pobi"
            val actual = Player(inputUsername, 1000)
            actual.name shouldBe "pobi"
            actual.ownedCards.size shouldBe 0
        }
    }

    describe("hit card test") {
        lateinit var player: Player
        beforeTest { player = Player("pobi", 1000) }

        it("사용자가 소유한 카드리스트에 hit 한 카드를 추가한다.") {
            val card = Card(Suit.SPADES, CardNumber.NINE)
            player.receiveCard(card)

            player.ownedCards.size shouldBe 1
            player.ownedCards[0] shouldBe card
        }
    }

    describe("플레이어의 카드 숫자를 모두 더한다") {
        lateinit var sut: Player
        context("ace가 없는 경우") {
            it("숫자를 모두 더한다.") {
                val cardList =
                    mutableListOf(
                        Card(Suit.SPADES, CardNumber.NINE),
                        Card(Suit.SPADES, CardNumber.TEN),
                    )
                sut = Player("pobi", 1000, cardList)
                sut.sumOfCard() shouldBe 19
            }

            it("J, Q, K 는 10으로 계산한다.") {
                val cardList =
                    mutableListOf(
                        Card(Suit.SPADES, CardNumber.KING),
                        Card(Suit.SPADES, CardNumber.JACK),
                    )
                sut = Player("pobi", 1000, cardList)
                val actual = sut.sumOfCard()
                actual shouldBe 20
            }
        }

        context("ACE를 가지고 있는 경우") {
            it("ACE는 1 또는 11로 계산할 수 있다") {
                val cardList =
                    mutableListOf(
                        Card(Suit.SPADES, CardNumber.ACE),
                        Card(Suit.SPADES, CardNumber.TEN),
                    )
                sut = Player("pobi", 1000, cardList)
                val actual = sut.sumOfCard()
                actual shouldBe 21
            }

            it("ACE를 계산할 때 21을 넘으면 안된다.") {
                val cardList =
                    mutableListOf(
                        Card(Suit.SPADES, CardNumber.ACE),
                        Card(Suit.CLUBS, CardNumber.ACE),
                    )
                sut = Player("pobi", 1000, cardList)
                val actual = sut.sumOfCard()
                actual shouldBe 12
            }
        }
    }

    describe("블랙잭") {
        context("처음 받은 두 장의 카드 합이 21인 경우") {
            it("should be true") {
                val cardList =
                    mutableListOf(
                        Card(Suit.SPADES, CardNumber.ACE),
                        Card(Suit.CLUBS, CardNumber.KING),
                    )

                val sut = Player(name = "pobi", bettingAmount = 1000, cardList)
                sut.isBlackjack() shouldBe true
            }
        }
    }
})
