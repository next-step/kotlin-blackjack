package blackjack.domain.game

import blackjack.domain.card.CardFactory
import blackjack.domain.card.Cards
import blackjack.domain.player.Gamer
import blackjack.domain.player.Gamers
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize

class GameTest : StringSpec({

    "플레이어에게 카드를 나눠준다." {
        val cards = Cards(CardFactory.defaultCards)
        val gamers = Gamers(listOf(Gamer("gray"), Gamer("jason")))
        val game = Game(cards, gamers)

        val john = gamers.gamers[0]
        val jane = gamers.gamers[1]

        game.drawCardToPlayer(john)
        game.drawCardToPlayer(jane)

        john.deck.getCards() shouldHaveSize 3
        jane.deck.getCards() shouldHaveSize 3
    }
})
