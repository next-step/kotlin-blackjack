package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSymbol
import blackjack.domain.cards
import blackjack.domain.gamePlayers
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayersTest : StringSpec({
    "딜러를 얻는다." {
        val dealer = Dealer(cards(Card(CardNumber.ACE, CardSymbol.CLUB), Card(CardNumber.TWO, CardSymbol.CLUB)))
        val gamePlayers = gamePlayers(
            GamePlayer(
                "test1",
                cards(Card(CardNumber.JACK, CardSymbol.CLUB), Card(CardNumber.QUEEN, CardSymbol.CLUB))
            ),
            GamePlayer(
                "test2",
                cards(Card(CardNumber.JACK, CardSymbol.DIAMOND), Card(CardNumber.QUEEN, CardSymbol.DIAMOND))
            ),
        )
        val players = Players(listOf(listOf(dealer), gamePlayers.players).flatten())

        players.getDealer() shouldBe dealer
    }

    "게임 플레이어를 얻는다." {
        val dealer = Dealer(cards(Card(CardNumber.ACE, CardSymbol.CLUB), Card(CardNumber.TWO, CardSymbol.CLUB)))
        val gamePlayers = gamePlayers(
            GamePlayer(
                "test1",
                cards(Card(CardNumber.JACK, CardSymbol.CLUB), Card(CardNumber.QUEEN, CardSymbol.CLUB))
            ),
            GamePlayer(
                "test2",
                cards(Card(CardNumber.JACK, CardSymbol.DIAMOND), Card(CardNumber.QUEEN, CardSymbol.DIAMOND))
            ),
        )
        val players = Players(listOf(listOf(dealer), gamePlayers.players).flatten())

        players.getGamePlayers().players shouldBe gamePlayers.players
    }
})
