package blackjack

import blackjack.state.Blackjack
import blackjack.state.Bust
import blackjack.state.Hit
import blackjack.state.Start
import blackjack.state.Stay
import io.kotest.assertions.throwables.shouldThrow
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
        sut.addCardToHand(Card(CardNumber.ACE, CardSuit.SPADE))

        // then
        sut.handSize() shouldBe 1
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

    test("플레이어의 손패가 21점 미만이라면, hit할 수 있다.") {
        // given
        val values = listOf(
            Card(CardNumber.TEN, CardSuit.SPADE),
            Card(CardNumber.TEN, CardSuit.SPADE)
        )
        val sut = Player(name = "gomding", hand = Cards(values), state = Start)

        // when
        sut.hit()

        // then
        sut.isHit().shouldBeTrue()
    }

    test("플레이어 21점 이상이라면, hit 상태가 될 수 없다.") {
        // given
        val values = listOf(
            Card(CardNumber.TEN, CardSuit.SPADE),
            Card(CardNumber.ACE, CardSuit.SPADE)
        )
        val sut = Player(name = "gomding", hand = Cards(values), state = Start)

        // when
        shouldThrow<IllegalStateException> { sut.hit() }
    }

    test("플레이어 손패 총 합이 20 이하라면 stay 상태가 될 수 있다.") {
        // given
        val values = listOf(
            Card(CardNumber.TEN, CardSuit.SPADE),
            Card(CardNumber.TEN, CardSuit.SPADE),
        )
        val sut = Player(name = "gomding", hand = Cards(values), state = Start)

        // when
        sut.stay()

        // then
        sut.isStay().shouldBeTrue()
    }

    test("플레이어 손패 총합이 21을 이상이면 stay 상태가 될 수 없다.") {
        // given
        val values = listOf(
            Card(CardNumber.TEN, CardSuit.SPADE),
            Card(CardNumber.ACE, CardSuit.SPADE),
        )
        val sut = Player(name = "gomding", hand = Cards(values), state = Start)

        // when then
        shouldThrow<IllegalStateException> { sut.stay() }
    }

    test("플레이어 손패 총합이 22 이상이면 bust상태가 될 수 있다.") {
        // given
        val values = listOf(
            Card(CardNumber.TEN, CardSuit.SPADE),
            Card(CardNumber.TEN, CardSuit.SPADE),
            Card(CardNumber.TWO, CardSuit.SPADE),
        )
        val sut = Player(name = "gomding", hand = Cards(values), state = Hit)

        // when
        sut.bust()

        // then
        sut.isBust().shouldBeTrue()
    }

    test("플레이어 손패 총합이 22 미만이면 bust상태가 될 수 없다.") {
        // given
        val values = listOf(
            Card(CardNumber.TEN, CardSuit.SPADE),
            Card(CardNumber.TEN, CardSuit.SPADE),
            Card(CardNumber.ACE, CardSuit.SPADE),
        )
        val sut = Player(name = "gomding", hand = Cards(values), state = Start)

        // when
        shouldThrow<IllegalStateException> { sut.bust() }
    }

    test("플레이어 손패 총합이 21이라면 blackjack 상태가 될 수 있다.") {
        // given
        val values = listOf(
            Card(CardNumber.TEN, CardSuit.SPADE),
            Card(CardNumber.TEN, CardSuit.SPADE),
            Card(CardNumber.ACE, CardSuit.SPADE),
        )
        val sut = Player(name = "gomding", hand = Cards(values), state = Start)

        // when
        sut.blackjack()

        // then
        sut.isBlackjack().shouldBeTrue()
    }

    test("플레이어 손패 총합이 21이 아니라면 blackjack 상태가 될 수 없다.") {
        // given
        val values = listOf(
            Card(CardNumber.TEN, CardSuit.SPADE),
            Card(CardNumber.TEN, CardSuit.SPADE),
            Card(CardNumber.TWO, CardSuit.SPADE),
        )
        val sut = Player(name = "gomding", hand = Cards(values), state = Start)

        // when
        shouldThrow<IllegalStateException> { sut.blackjack() }
    }
})
