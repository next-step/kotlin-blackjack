package blackjack.domain.game

import blackjack.domain.card.CardFactory
import blackjack.domain.card.Cards
import blackjack.domain.player.Dealer
import blackjack.domain.player.Gamer
import blackjack.domain.player.Gamers
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize

class GameTest : StringSpec({

    "플레이어에게 카드를 나눠준다." {
        val cards = Cards(CardFactory.defaultCards)
        val gamers = Gamers(listOf(Gamer("gray"), Gamer("jason")))
        val dealer = Dealer()
        val game = Game(cards, dealer, gamers)

        val john = gamers.gamers[0]
        val jane = gamers.gamers[1]

        game.drawCardToPlayer(john)
        game.drawCardToPlayer(jane)

        john.deck.getCards() shouldHaveSize 3
        jane.deck.getCards() shouldHaveSize 3
    }

    "딜러에게 카드를 한 장 추가할 수 있다." {
        val cards = Cards(CardFactory.defaultCards)
        val dealer = Dealer()
        val gamers = Gamers(listOf(Gamer("gray"), Gamer("jason")))
        val game = Game(cards, dealer, gamers)

        game.drawCardToDealer()

        dealer.deck.getCards() shouldHaveSize 3
    }
})
