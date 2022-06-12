package blackjack.domain.blackjack

import blackjack.domain.card.Ace
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.NumberCard
import blackjack.domain.card.Queen
import blackjack.domain.card.Suit
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.score.Match
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly

class BlackJackResultTest : DescribeSpec({

    describe("of") {
        it("각 플레이어별로 승패를 확인할 수 있다") {
            val yohan = Player(
                "yohan",
                cards = Cards(
                    listOf(
                        Card(Suit.DIAMOND, Queen()),
                        Card(Suit.DIAMOND, Ace()),
                    )
                )
            )
            val pang = Player(
                "pang",
                cards = Cards(
                    listOf(
                        Card(Suit.DIAMOND, Queen()),
                        Card(Suit.DIAMOND, NumberCard(2)),
                    )
                )
            )
            val dealer = Dealer(
                cards = Cards(
                    listOf(
                        Card(Suit.DIAMOND, Queen()),
                        Card(Suit.DIAMOND, NumberCard(9)),
                    )
                )
            )

            val result = BlackJackResult.of(listOf(yohan, pang), dealer)

            assertSoftly {
                result.dealerResult.matches shouldContainExactly listOf(Match.LOSE, Match.WIN)
                result.playerResults.map { it.match } shouldContainExactly listOf(Match.WIN, Match.LOSE)
            }
        }
    }
})
