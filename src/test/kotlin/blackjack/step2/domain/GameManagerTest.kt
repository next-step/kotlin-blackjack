package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class GameManagerTest : FunSpec({
    val cardPicker = RandomCardPicker()
    val gameManager = GameManager(cardPicker)

    test("딜러는 카드의 합이 17점 미만일 경우 카드를 추가로 받는다.") {
        // given
        val dealer =
            Dealer(
                cards =
                    Cards.of(
                        listOf(
                            Card(CardNumber.TEN, CardType.SPADE),
                            Card(CardNumber.SIX, CardType.HEART),
                        ),
                    ),
            )

        // when
        val finalDealer = gameManager.playTurn(dealer) as Dealer

        // then
        finalDealer.cards.all.size shouldBe 3
    }

    test("딜러는 카드의 합이 17이상인 경우 카드를 받을 수 없다.") {
        // given
        val dealer =
            Dealer(
                cards =
                    Cards.of(
                        listOf(
                            Card(CardNumber.TEN, CardType.SPADE),
                            Card(CardNumber.SEVEN, CardType.HEART),
                        ),
                    ),
            )

        // when
        val finalDealer = gameManager.playTurn(dealer) as Dealer

        // then
        finalDealer.cards.all.size shouldBe 2
    }

    test("플레이어는 Bust(21 초과)시 더 이상 카드를 받을 수 없다.") {
        // given
        val player =
            Player(
                name = "dongyeon",
                cards =
                    Cards.of(
                        listOf(
                            Card(CardNumber.TEN, CardType.SPADE),
                            Card(CardNumber.JACK, CardType.HEART),
                            Card(CardNumber.TWO, CardType.CLOVER),
                        ),
                    ),
            )

        // when
        val finalPlayer = gameManager.playTurn(player) as Player

        // then
        finalPlayer.score() shouldBe 22
        finalPlayer.cards.all.size shouldBe 3
    }
})
