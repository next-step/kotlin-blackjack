package blackjack

import blackjack.domain.Dealer
import blackjack.domain.PlayerStatus
import blackjack.infrastructure.RandomDeck
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class BlackJackGameTest : StringSpec({
    "게임에 참여한 유저들의 정보와 한명의 딜러의 정보를 가지고 있다." {
        val game = BlackJackGame.createGame(createPlayerInfos(2), RandomDeck())

        game.players.members.size shouldBe 2
        game.dealer.shouldBeInstanceOf<Dealer>()
    }

    "게임이 시작되면 각 유저들은 2개의 패를 지급받는다." {
        val game = BlackJackGame.createGame(createPlayerInfos(2), RandomDeck())

        game.players.members.forEach {
            it.hand.cards.size() shouldBe 2
        }
    }

    "유저들에게 최초 지급된 패가 21일 경우 블랙잭을 선언한다." {
        val game = BlackJackGame.createGame(createPlayerInfos(2), aceDeck())

        game.checkBlackJack() shouldBe true
    }

    "유저에게 패를 한장 지급할 수 있다." {
        val game = BlackJackGame.createGame(createPlayerInfos(2), RandomDeck())
        val currentPlayer = game.currentPlayer
        game.hit(currentPlayer)

        currentPlayer.hand.cards.size() shouldBe 3
    }

    "유저가 더이상 패를 지급하지 못하도록 상태를 변경할 수 있다." {
        val game = BlackJackGame.createGame(createPlayerInfos(2), RandomDeck())
        val currentPlayer = game.currentPlayer
        game.stay(currentPlayer)

        currentPlayer.status shouldBe PlayerStatus.STAY
        currentPlayer.hand.cards.size() shouldBe 2
    }

    "딜러의 패가 16이하일 경우 한장을 뽑도록 한다." {
        val game = BlackJackGame.createGame(createPlayerInfos(2), basicDeck())

        game.handleDealerChance()

        game.dealer.hand.cards.size() shouldBe 3
    }
})
