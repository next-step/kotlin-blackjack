package blackjack.domain

import blackjack.view.ResultView
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class BlackJackGameTest : StringSpec({
    lateinit var game: BlackJackGame

    beforeEach {
        val names = listOf("플레이어2", "플레이어2")
        val users = names.map { User(Name(it)) }
        val dealer = Dealer()
        game = BlackJackGame(deck = Deck(), users = Users(users), dealer = dealer)
    }

    "2명의 참가자가 게임을 시작할 때 카드를 2장씩 지급한다." {
        game.drawInitCards()

        game.users.values.forEach {
            it.cards.values.size shouldBe 2
        }
    }

    "딜러가 게임을 시작할 때 2장의 카드를 지급한다." {
        game.drawInitCards()
        val initialCardSize = game.dealer.cards.values.size

        initialCardSize shouldBe 2
    }

    "플레이어 수만큼의 플레이어 결과를 반환한다." {
        val users = listOf(
            User(Name("1")), User(Name("2")), User(Name("3"))
        )
        val dealer = Dealer()
        val newGame = BlackJackGame(deck = Deck(), dealer = dealer, users = Users(users))

        val playerResults = newGame.getPlayerResults()

        playerResults.size shouldBe 3
    }

    "딜러가 플레이할 때 가진 카드의 점수가 16점 이하이면 hit 을 한다." {
        val dealer = Dealer(Card(Suite.SPADE, Denomination.JACK), Card(Suite.CLOVER, Denomination.THREE))
        val newGame = game.copy(dealer = dealer)

        newGame.playDealer { ResultView.printDealerHitOrStay(it) }
        newGame.dealer.cards.values.size shouldBe 3
    }
})
