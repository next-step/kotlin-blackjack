package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Player private constructor(
    val name: String,
    val cards: List<Card> = mutableListOf()
) {

    companion object {
        fun of(name: String, deck: Deck): Player {
            return Player(name, mutableListOf(deck.getOneCard(), deck.getOneCard()))
        }
    }
}

class PlayerTest : StringSpec({
    "플레이어는 초기에 카드를 2장씩 받는다." {
        val deck = Deck.makeDeck()
        val player = Player.of("pavlo", deck)
        player.cards.size shouldBe 2
    }
})
