package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ResultManagerTest : FunSpec({
    test("딜러가 버스트한 경우, 모든 플레이어가 승리한다.") {
        val dealer =
            Dealer(
                cards =
                    Cards.of(
                        listOf(
                            Card(CardNumber.TEN, CardType.SPADE),
                            Card(CardNumber.JACK, CardType.HEART),
                            Card(CardNumber.TWO, CardType.DIAMOND),
                        ),
                    ),
            )
        val players =
            listOf(
                Player("dongyeon", Cards.of(listOf(Card(CardNumber.FIVE, CardType.CLOVER), Card(CardNumber.THREE, CardType.HEART)))),
                Player("pobi", Cards.of(listOf(Card(CardNumber.TEN, CardType.SPADE), Card(CardNumber.SIX, CardType.DIAMOND)))),
            )

        val results = ResultManager.calculate(listOf(dealer) + players)

        results.first { it.participant is Dealer }.resultTypes shouldBe listOf(GameResultType.LOSE, GameResultType.LOSE)
        results.filter { it.participant is Player }.forEach {
            it.resultTypes shouldBe listOf(GameResultType.WIN)
        }
    }

    test("플레이어와 딜러의 점수가 동일한 경우, 무승부로 처리된다.") {
        val dealer =
            Dealer(
                cards = Cards.of(listOf(Card(CardNumber.TEN, CardType.SPADE), Card(CardNumber.QUEEN, CardType.HEART))),
            )
        val players =
            listOf(
                Player("dongyeon", Cards.of(listOf(Card(CardNumber.JACK, CardType.CLOVER), Card(CardNumber.QUEEN, CardType.DIAMOND)))),
            )

        val results = ResultManager.calculate(listOf(dealer) + players)

        results.first { it.participant is Dealer }.resultTypes shouldBe listOf(GameResultType.DRAW)
        results.first { it.participant is Player }.resultTypes shouldBe listOf(GameResultType.DRAW)
    }

    test("플레이어가 딜러보다 높은 점수를 가지면 승리한다.") {
        val dealer =
            Dealer(
                cards = Cards.of(listOf(Card(CardNumber.TEN, CardType.HEART), Card(CardNumber.SIX, CardType.DIAMOND))),
            )
        val players =
            listOf(
                Player("dongyeon", Cards.of(listOf(Card(CardNumber.TEN, CardType.SPADE), Card(CardNumber.JACK, CardType.HEART)))),
            )

        val results = ResultManager.calculate(listOf(dealer) + players)

        results.first { it.participant is Dealer }.resultTypes shouldBe listOf(GameResultType.LOSE)
        results.first { it.participant is Player }.resultTypes shouldBe listOf(GameResultType.WIN)
    }

    test("플레이어가 버스트한 경우, 딜러가 승리한다.") {
        val dealer =
            Dealer(
                cards = Cards.of(listOf(Card(CardNumber.NINE, CardType.HEART), Card(CardNumber.SIX, CardType.CLOVER))),
            )
        val players =
            listOf(
                Player(
                    "dongyeon",
                    Cards.of(
                        listOf(
                            Card(CardNumber.TEN, CardType.DIAMOND),
                            Card(CardNumber.JACK, CardType.SPADE),
                            Card(CardNumber.TWO, CardType.HEART),
                        ),
                    ),
                ),
            )

        val results = ResultManager.calculate(listOf(dealer) + players)

        results.first { it.participant is Dealer }.resultTypes shouldBe listOf(GameResultType.WIN)
        results.first { it.participant is Player }.resultTypes shouldBe listOf(GameResultType.LOSE)
    }

    test("딜러와 여러 플레이어의 복합적인 승패 계산") {
        val dealer =
            Dealer(
                cards = Cards.of(listOf(Card(CardNumber.TEN, CardType.SPADE), Card(CardNumber.FOUR, CardType.HEART))),
            )
        val players =
            listOf(
                // 승리
                Player("dongyeon", Cards.of(listOf(Card(CardNumber.JACK, CardType.SPADE), Card(CardNumber.FIVE, CardType.DIAMOND)))),
                // 패배
                Player("pobi", Cards.of(listOf(Card(CardNumber.NINE, CardType.HEART), Card(CardNumber.TWO, CardType.CLOVER)))),
                // 무승부
                Player("jason", Cards.of(listOf(Card(CardNumber.TEN, CardType.SPADE), Card(CardNumber.FOUR, CardType.HEART)))),
            )

        val results = ResultManager.calculate(listOf(dealer) + players)

        val dealerResults = results.first { it.participant is Dealer }.resultTypes
        dealerResults.count { it == GameResultType.WIN } shouldBe 1
        dealerResults.count { it == GameResultType.LOSE } shouldBe 1
        dealerResults.count { it == GameResultType.DRAW } shouldBe 1

        val playerResults = results.filter { it.participant is Player }
        playerResults[0].resultTypes shouldBe listOf(GameResultType.WIN)
        playerResults[1].resultTypes shouldBe listOf(GameResultType.LOSE)
        playerResults[2].resultTypes shouldBe listOf(GameResultType.DRAW)
    }
})
