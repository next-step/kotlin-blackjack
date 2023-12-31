package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.player.DealerPlayer
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.mock.card
import blackjack.mock.deck
import blackjack.mock.hand
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DealerTest : DescribeSpec({
    describe("dealCards") {
        val count = 2
        context("플레이어 1명에게 ${count}장 카드를 배분하면") {
            val cards = listOf(
                Card(Suit.CLUB, Rank.ACE),
                Card(Suit.CLUB, Rank.TWO),
                Card(Suit.DIAMOND, Rank.THREE),
                Card(Suit.DIAMOND, Rank.FOUR),
            )
            val dealer = Dealer(deck(cards))
            val player = Player(PlayerName("홍길동"), { Action.HIT })

            dealer.dealCards(count, player)

            it("플레이어에게 카드가 전달된다") {
                player.hand.cards shouldBe listOf(cards[3], cards[2])
            }

            it("덱에서 카드가 제거된다") {
                dealer.deck.cards shouldBe listOf(cards[0], cards[1])
            }
        }

        context("플레이어 2명에게 ${count}장씩 카드 배분하면") {
            val deckCards = deck(
                Card(Suit.CLUB, Rank.ACE),
                Card(Suit.CLUB, Rank.TWO),
                Card(Suit.DIAMOND, Rank.THREE),
                Card(Suit.DIAMOND, Rank.FOUR),
                Card(Suit.HEART, Rank.FIVE),
                Card(Suit.HEART, Rank.SIX),
            )
            val dealer = Dealer(deckCards)
            val players = listOf(
                Player(PlayerName("홍길동"), { Action.HIT }),
                Player(PlayerName("김길동"), { Action.HIT }),
            )

            dealer.dealCards(count, *players.toTypedArray())

            it("플레이어에게 해당 카드가 전달된다") {
                players[0].hand.cards.size shouldBe 2
                players[1].hand.cards.size shouldBe 2
            }

            it("덱에서 카드는 제거된다") {
                dealer.deck.cards.size shouldBe 2
            }
        }
    }

    describe("dealToSelf") {
        val cards = listOf(
            Card(Suit.CLUB, Rank.ACE),
            Card(Suit.CLUB, Rank.TWO),
            Card(Suit.DIAMOND, Rank.THREE),
            Card(Suit.DIAMOND, Rank.FOUR),
        )
        val dealer = Dealer(deck(cards))

        val count = 2
        context("딜러가 자신에게 ${count}장 카드를 배분하면") {
            dealer dealToSelf count

            it("플레이어에게 해당 카드기 전달된다") {
                dealer.hand.cards shouldBe listOf(cards[3], cards[2])
            }

            it("덱에서 카드가 제거된다") {
                dealer.deck.cards shouldBe listOf(cards[0], cards[1])
            }
        }
    }

    describe("score") {
        val dealer = Dealer(
            player = DealerPlayer(hand(card(Rank.ACE), card(Rank.QUEEN)))
        )
        context("딜러가 가진 카드의 점수를 조회하면") {
            val result = dealer.score

            it("계산된 점수가 반환된다") {
                result.value shouldBe 21
            }
        }
    }

    describe("isGreaterScoreThan") {
        val score20cards = hand(card(Rank.QUEEN), card(Rank.QUEEN))
        val dealer = DealerPlayer(score20cards)
        context("딜러보다 낮은 점수로 비교하면") {
            val result = dealer isGreaterScoreThan 16

            it("참을 반환한다") {
                result shouldBe true
            }
        }
        context("딜러보다 높은 점수로 비교하면") {
            val result = dealer isGreaterScoreThan 21

            it("거짓을 반환한다") {
                result shouldBe false
            }
        }
        context("딜러와 같은 점수로 비교하면") {
            val result = dealer isGreaterScoreThan 20

            it("거짓을 반환한다") {
                result shouldBe false
            }
        }
    }
})
