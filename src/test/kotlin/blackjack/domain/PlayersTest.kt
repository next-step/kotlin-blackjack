package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSymbol
import blackjack.domain.player.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayersTest : StringSpec({
    "플레이어가 카드를 뽑는다." {
        // given
        val cards = cards(Card(CardNumber.ACE, CardSymbol.SPADE))
        val player = Player("tris", cards)

        // when
        player.draw(Card(CardNumber.TWO, CardSymbol.SPADE))

        // then
        player.cards shouldBe cards(
            Card(CardNumber.ACE, CardSymbol.SPADE),
            Card(CardNumber.TWO, CardSymbol.SPADE),
        )
    }
})
