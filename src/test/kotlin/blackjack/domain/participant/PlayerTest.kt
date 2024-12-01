package blackjack.domain.participant

import blackjack.domain.betting.Betting
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "플레이어는 이름과 소유한 카드 정보를 갖는다." {
        val card = Card(shape = CardShape.Heart, number = CardNumber.Two)
        val cards = Cards()
        cards.add(card)
        val player = Player(name = "홍길동", cards = cards, betting = Betting(100))

        val cardResult = player.cards.getCards()[0]
        player.name shouldBe "홍길동"
        cardResult.shape shouldBe CardShape.Heart
        cardResult.number shouldBe CardNumber.Two
    }

    "플레이어는 카드를 받을 수 있다." {
        val player = Player(name = "홍길동", betting = Betting(100))
        val card = Card(shape = CardShape.Heart, number = CardNumber.Ace)

        player.receiveCard(card)

        player.cards.getCards()[0] shouldBe card
    }

    "플레이어는 소유한 카드의 다음 받을 카드 포함 소유한 모든 카드 숫자 합이 21이 넘으면 카드를 받을 수 없다." {
        val cards =
            Cards(
                listOf(
                    Card(shape = CardShape.Heart, number = CardNumber.Ten),
                    Card(shape = CardShape.Spade, number = CardNumber.Ten),
                    Card(shape = CardShape.Diamond, number = CardNumber.Ten),
                ).toMutableList(),
            )

        val player = Player(name = "홍길동", cards = cards, betting = Betting(100))

        player.canReceive() shouldBe false
    }
})
