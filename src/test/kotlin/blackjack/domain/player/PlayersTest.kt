package blackjack.domain.player

import blackjack.domain.deck.Deck
import blackjack.domain.game.START_DRAW_COUNT
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PlayersTest : StringSpec({
    "플레이어들이 생성되고 게임을 준비하면 각각 카드를 ${START_DRAW_COUNT}장씩 나눠받는다." {
        val deck = Deck.makeDeck()
        val players = Players.of(listOf("pavlo", "wade"))
        players.prepareGame(deck)
        players.players.forAll {
            it.getHands().size shouldBe START_DRAW_COUNT
        }
    }
})
