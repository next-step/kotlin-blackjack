package blackjack.step2.domain

import blackjack.step2.view.ConsoleGameInteractor
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PlayerTest : FunSpec({
    test("pickCard() 테스트 - 플레이어에게 카드를 추가로 줄 수 있다.") {
        // given
        val player = Player("dongyeon")
        val card = Card(CardNumber.TWO, CardType.SPADE)

        // when
        val pickedPlayer = player.pickCard(card)

        // then
        pickedPlayer.cards.all.size shouldBe 1
    }

    test("canDraw() 테스트 - 버스트 상태가 아니면 추가로 카드를 뽑을 수 있다.") {
        // given
        val player = Player("dongyeon")
        val card = Card(CardNumber.TWO, CardType.SPADE)

        // when
        val pickedPlayer = player.pickCard(card)

        // then
        pickedPlayer.canDraw() shouldBe true
    }

    test("playTurn() 테스트 - 플레이어가 버스트 상태이면 카드를 추가로 받을 수 없다.") {
        // given
        val cards =
            Cards.of(
                listOf(
                    Card(CardNumber.TEN, CardType.SPADE),
                    Card(CardNumber.SEVEN, CardType.HEART),
                    Card(CardNumber.SIX, CardType.HEART),
                ),
            )
        val player = Player(name = "dongyeon", cards = cards)
        val cardPicker = RandomCardPicker()
        val interactor = ConsoleGameInteractor()

        // when
        val playedPlayer = player.playTurn(cardPicker, interactor)

        // then
        playedPlayer.cards.all.size shouldBe 3
    }
})
