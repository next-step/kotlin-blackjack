package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.Hand
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DealerTest : DescribeSpec({
    describe("카드 배분") {
        val cards = mutableListOf(
            Card(Suit.CLUB, Rank.ACE),
            Card(Suit.CLUB, Rank.TWO),
            Card(Suit.DIAMOND, Rank.THREE),
            Card(Suit.DIAMOND, Rank.FOUR),
        )
        val dealer = Dealer(Deck(ArrayDeque(cards)))

        context("플레이어에게 장수만큼 카드 배분") {
            val count = 2
            val player = Player(PlayerName("홍길동"))

            dealer.dealCards(player, count)

            it("플레이어에게 카드 전달") {
                player.hand shouldBe Hand(
                    mutableListOf(
                        cards[3],
                        cards[2],
                    )
                )
            }

            it("딜러가 가진 카드에서 제거") {
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
})
