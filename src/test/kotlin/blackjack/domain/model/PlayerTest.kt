package blackjack.domain.model

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PlayerTest : FunSpec({
    test("점수 합이 21 미만일 경우 카드 뽑기 가능 여부 메소드 true 반환 테스트") {
        val name = PlayerName("홍길동")
        val cards = Cards.of(
            Card.of(CardNumber.KING, CardShape.Spade),
            Card.of(CardNumber.JACK, CardShape.Spade)
        )

        val player = Player(name, cards)

        player.isPossibleToDraw() shouldBe true
    }

    test("카드 A를 포함한 상태에서 점수 합이 21 미만이 가능할 경우 카드 뽑기 가능 여부 메소드 true 반환 테스트") {
        val name = PlayerName("홍길동")
        val cards = Cards.of(
            Card.of(CardNumber.ACE, CardShape.Spade),
            Card.of(CardNumber.TEN, CardShape.Spade)
        )

        val player = Player(name, cards)

        player.isPossibleToDraw() shouldBe true
    }

    test("점수 합이 21일 경우 카드 뽑기 가능 여부 메소드 false 반환 테스트") {
        val name = PlayerName("홍길동")
        val cards = Cards.of(
            Card.of(CardNumber.TWO, CardShape.Spade),
            Card.of(CardNumber.NINE, CardShape.Spade),
            Card.of(CardNumber.TEN, CardShape.Spade),
        )

        val player = Player(name, cards)

        player.isPossibleToDraw() shouldBe false
    }

    test("점수 합이 21 초과일 경우 카드 뽑기 가능 여부 메소드 false 반환 테스트") {
        val name = PlayerName("홍길동")
        val cards = Cards.of(
            Card.of(CardNumber.THREE, CardShape.Spade),
            Card.of(CardNumber.NINE, CardShape.Spade),
            Card.of(CardNumber.TEN, CardShape.Spade),
        )

        val player = Player(name, cards)

        player.isPossibleToDraw() shouldBe false
    }

    test("카드 A를 포함한 상태에서 점수 합이 반드시 21 이상일 경우 카드 뽑기 가능 여부 메소드 false 반환 테스트") {
        val name = PlayerName("홍길동")
        val cards = Cards.of(
            Card.of(CardNumber.ACE, CardShape.Spade),
            Card.of(CardNumber.TEN, CardShape.Spade),
            Card.of(CardNumber.JACK, CardShape.Spade),
        )

        val player = Player(name, cards)

        player.isPossibleToDraw() shouldBe false
    }
})
