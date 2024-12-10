package blackjack.step2.domain

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
})
