package blackjack

import blackjack.state.Blackjack
import blackjack.state.Bust
import blackjack.state.Hit
import blackjack.state.Start
import blackjack.state.Stay
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

internal class PlayerTest : FunSpec({
    test("손패에 가지고 있는 카드의 개수를 알 수 있다.") {
        // given
        val values = ArrayDeque(listOf(Card(CardNumber.ACE, CardSuit.SPADE)))
        val sut = Player(name = "gomding", Cards(values))

        // when
        val result = sut.handSize()

        // then
        result shouldBe 1
    }

    test("플레이어는 손패에 카드를 추가할 수 있다.") {
        // given
        val sut = Player(name = "gomding")

        // when
        val result: Player = sut.addCardToHand(Card(CardNumber.ACE, CardSuit.SPADE))

        // then
        result.handSize() shouldBe 1
    }

    test("플레이어가 게임을 시작한 상태인지 확인할 수 있다.") {
        // given
        val sut = Player(name = "gomding", state = Start)

        // when
        val result = sut.isStart()

        // then
        result.shouldBeTrue()
    }

    test("플레이어가 카드를 한장 더 받는 상태인지 확인할 수 있다.") {
        // given
        val sut = Player(name = "gomding", state = Hit)

        // when
        val result = sut.isHit()

        // then
        result.shouldBeTrue()
    }

    test("플레이어가 버스트된 상태인지 확인할 수 있다.") {
        // given
        val sut = Player(name = "gomding", state = Bust)

        // when
        val result = sut.isBust()

        // then
        result.shouldBeTrue()
    }

    test("플레이어가 카드를 더 받지 않기로 한 상태인지 확인할 수 있다.") {
        // given
        val sut = Player(name = "gomding", state = Stay)

        // when
        val result = sut.isStay()

        // then
        result.shouldBeTrue()
    }

    test("플레이어가 블랙잭 상태인지 확인할 수 있다.") {
        // given
        val sut = Player(name = "gomding", state = Blackjack)

        // when
        val result = sut.isBlackjack()

        // then
        result.shouldBeTrue()
    }
})
