package blackjack.domain.player

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
        val player = Player(name = "홍길동", cards = cards)

        val cardResult = player.cards.getCards()[0]
        player.name shouldBe "홍길동"
        cardResult.shape shouldBe CardShape.Heart
        cardResult.number shouldBe CardNumber.Two
    }

    "플레이어는 카드를 받을 수 있다." {
        val player = Player(name = "홍길동")
        val card = Card(shape = CardShape.Heart, number = CardNumber.Ace)

        player.receiveCard(card)

        player.cards.getCards()[0] shouldBe card
    }
})
