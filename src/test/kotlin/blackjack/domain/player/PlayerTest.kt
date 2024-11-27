package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "플레이어는 이름과 소유한 카드 정보를 갖는다." {
        val card = Card(shape = CardShape.Heart, number = CardNumber.Two)
        val player = Player(name = "홍길동", cards = listOf(card))

        val cardResult = player.cards[0]
        player.name shouldBe "홍길동"
        cardResult.shape shouldBe CardShape.Heart
        cardResult.number shouldBe CardNumber.Two
    }
})
