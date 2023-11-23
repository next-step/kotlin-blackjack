package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.Hand
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.player.DealerPlayer
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DealerTest : DescribeSpec({
    describe("dealCards") {
        val count = 2
        context("플레이어 1명에게 ${count}장 카드 배분") {
            val cards = mutableListOf(
                Card(Suit.CLUB, Rank.ACE),
                Card(Suit.CLUB, Rank.TWO),
                Card(Suit.DIAMOND, Rank.THREE),
                Card(Suit.DIAMOND, Rank.FOUR),
            )
            val dealer = Dealer(Deck(ArrayDeque(cards)))
            val player = Player(PlayerName("홍길동"), { Action.HIT })

            dealer.dealCards(count, player)

            it("플레이어에게 카드 전달") {
                player.hand shouldBe Hand(
                    mutableListOf(
                        cards[3],
                        cards[2],
                    )
                )
            }

            it("덱에서 카드에서 제거") {
                dealer.deck shouldBe Deck(
                    ArrayDeque(
                        mutableListOf(
                            cards[0],
                            cards[1]
                        )
                    )
                )
            }
        }

        context("플레이어 2명에게 ${count}장씩 카드 배분") {
            val cards = mutableListOf(
                Card(Suit.CLUB, Rank.ACE),
                Card(Suit.CLUB, Rank.TWO),
                Card(Suit.DIAMOND, Rank.THREE),
                Card(Suit.DIAMOND, Rank.FOUR),
                Card(Suit.HEART, Rank.FIVE),
                Card(Suit.HEART, Rank.SIX),
            )
            val dealer = Dealer(Deck(ArrayDeque(cards)))
            val players = listOf(
                Player(PlayerName("홍길동"), { Action.HIT }),
                Player(PlayerName("김길동"), { Action.HIT }),
            )

            dealer.dealCards(count, *players.toTypedArray())

            it("플레이어에게 카드 전달") {
                players[0].hand.cards.size shouldBe 2
                players[1].hand.cards.size shouldBe 2
            }

            it("덱에서 카드에서 제거") {
                dealer.deck.cards.size shouldBe 2
            }
        }
    }

    describe("dealToSelf") {
        val cards = mutableListOf(
            Card(Suit.CLUB, Rank.ACE),
            Card(Suit.CLUB, Rank.TWO),
            Card(Suit.DIAMOND, Rank.THREE),
            Card(Suit.DIAMOND, Rank.FOUR),
        )
        val dealer = Dealer(Deck(ArrayDeque(cards)))

        val count = 2
        context("자신에게 ${count}장 카드 배분") {
            dealer.dealToSelf(count)

            it("플레이어에게 카드 전달") {
                dealer.hand shouldBe Hand(
                    mutableListOf(
                        cards[3],
                        cards[2],
                    )
                )
            }

            it("덱에서 카드 제거") {
                dealer.deck shouldBe Deck(
                    ArrayDeque(
                        mutableListOf(
                            cards[0],
                            cards[1]
                        )
                    )
                )
            }
        }
    }

    describe("score") {
        val dealer = Dealer(
            dealerPlayer = DealerPlayer(Hand(mutableListOf(Card(Suit.HEART, Rank.ACE), Card(Suit.DIAMOND, Rank.QUEEN))))
        )
        context("딜러가 가진 카드의 점수 조회") {
            val result = dealer.score

            it("계산된 점수 반환") {
                result.value shouldBe 21
            }
        }
    }

    describe("isScoreGreaterThan") {
        val score20cards = mutableListOf(Card(Suit.HEART, Rank.QUEEN), Card(Suit.DIAMOND, Rank.QUEEN))
        val dealer = DealerPlayer(Hand(score20cards))
        context("딜러보다 낮은 점수로 비교하면") {
            val result = dealer.isScoreGreaterThan(16)

            it("참을 반환") {
                result shouldBe true
            }
        }
        context("딜러보다 높은 점수로 비교하면") {
            val result = dealer.isScoreGreaterThan(21)

            it("거짓을 반환") {
                result shouldBe false
            }
        }
        context("딜러와 같은 점수로 비교하면") {
            val result = dealer.isScoreGreaterThan(20)

            it("거짓을 반환") {
                result shouldBe false
            }
        }
    }
})
