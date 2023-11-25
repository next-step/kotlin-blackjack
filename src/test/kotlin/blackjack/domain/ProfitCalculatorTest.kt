package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ProfitCalculatorTest : BehaviorSpec({
    Given("플레이어와 딜러가 주어지면") {
        When("수익 계산기는") {
            val profitCalculator = ProfitCalculator(mapOf(Pair("yeongun", BettingMoney(10000))))
            Then("플레이어의 수익을 반환한다.") {
                forAll(
                    row(
                        // 딜러 & 플레이어 BLACKJACK
                        listOf(Card(CardSuit.CLUB, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.ACE)),
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.ACE)),
                        null,
                        null,
                        0
                    ),
                    row(
                        // 플레이어 BLACKJACK
                        listOf(Card(CardSuit.CLUB, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TEN)),
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.ACE)),
                        null,
                        null,
                        15000
                    ),
                    row(
                        // 딜러 Bust
                        listOf(Card(CardSuit.CLUB, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TWO)),
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.EIGHT)),
                        Card(CardSuit.CLUB, CardNumber.TEN),
                        null,
                        10000
                    ),
                    row(
                        // 플레이어 Bust
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)),
                        listOf(Card(CardSuit.CLUB, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TEN)),
                        null,
                        Card(CardSuit.CLUB, CardNumber.TWO),
                        -10000
                    ),
                    row(
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)),
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.EIGHT)),
                        null,
                        null,
                        -10000
                    ),
                    row(
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)),
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TEN)),
                        null,
                        null,
                        10000
                    ),
                    row(
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)),
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)),
                        null,
                        null,
                        0
                    ),
                ) { dealerInitCards, playerInitCards, dealerHitCard, playerHitCard, expected ->
                    val dealer = Dealer(FixedDeck())
                    val player = Player("yeongun", Hand())
                    dealer.init(dealerInitCards)
                    player.init(playerInitCards)
                    dealerHitCard?.let { dealer.hit(it) }
                    playerHitCard?.let { player.hit(it) }
                    profitCalculator.getPlayerProfit(player, dealer) shouldBe expected
                }
            }
        }
    }
})
