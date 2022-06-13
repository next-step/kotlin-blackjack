package blackjack.application

import blackjack.domain.CustomDeck
import blackjack.domain.card.Card
import blackjack.domain.card.Denomination.FIVE
import blackjack.domain.card.Denomination.FOUR
import blackjack.domain.card.Denomination.SEVEN
import blackjack.domain.card.Denomination.SIX
import blackjack.domain.card.Denomination.THREE
import blackjack.domain.card.Denomination.TWO
import blackjack.domain.card.Suit.CLOVER
import blackjack.domain.card.Suit.DIAMOND
import blackjack.domain.card.Suit.HEART
import blackjack.domain.card.Suit.SPADE
import blackjack.domain.cards
import blackjack.domain.hand
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Deck
import blackjack.domain.participant.Player
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.equality.shouldBeEqualToComparingFields

class BlackJackTests : DescribeSpec({

    describe("블랙잭은") {
        it("2명에서 6명 사이의 참가자가 있어야 진행 가능하다") {
            io.kotest.data.forAll(
                table(
                    headers("딜러", "참가자"),
                    row(
                        Dealer(), listOf(Player("1"), Player("2")),
                    ),
                    row(
                        Dealer(), List(6) { Player(it.toString()) }
                    ),
                )
            ) { dealer, players ->
                shouldNotThrowAny {
                    BlackJack(dealer, players)
                }
            }
        }

        it("참가자가 2명에서 6명 사이가 아니라면 진행 가능할 수 없다") {
            io.kotest.data.forAll(
                table(
                    headers("딜러", "참가자"),
                    row(
                        Dealer(), listOf(Player("1")),
                    ),
                    row(
                        Dealer(), List(7) { Player(it.toString()) }
                    ),
                )
            ) { dealer, players ->
                shouldThrowExactly<IllegalArgumentException> {
                    BlackJack(dealer, players)
                }
            }
        }

        it("딜러와 모든 참가자에게 카드를 2장 씩 분배할 수 있다") {
            val deck: Deck = CustomDeck(
                cards(
                    Card(TWO, CLOVER), Card(THREE, HEART),
                    Card(FOUR, HEART), Card(FIVE, SPADE),
                    Card(SIX, DIAMOND), Card(SEVEN, CLOVER)
                )
            )
            val dealer = Dealer(deck)
            val participants = listOf(Player("1"), Player("2"))
            val blackjack = BlackJack(dealer, participants)
            blackjack.distribute()
            dealer.hand shouldBeEqualToComparingFields hand(Card(TWO, CLOVER), Card(THREE, HEART))
            participants[0].hand shouldBeEqualToComparingFields hand(Card(TWO, CLOVER), Card(THREE, HEART))
            participants[1].hand shouldBeEqualToComparingFields hand(Card(SIX, DIAMOND), Card(SEVEN, CLOVER))
        }
    }
})
