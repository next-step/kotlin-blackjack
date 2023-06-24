package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize

class GameTest : FunSpec({

    test("팩토리 함수로 게임을 빌딩할 수 있다.") {
        val actual = Game.from(listOf("catsbi" to 1000.0, "pobi" to 2000.0))

        actual.players shouldHaveSize 2
    }

    test("플레이어에게 카드 분배를 할 수 있다.") {
        val game = Game.from(listOf("catsbi" to 1000.0, "pobi" to 2000.0))
        game.dealInitialCards()

        game.players.forEach { player ->
            val actual = player.currentDeck()
            actual.toList() shouldHaveSize 2
        }
    }

    test("원하는 플레이어에게 카드를 분배할 수 있다.") {
        val game = Game.from(listOf("catsbi" to 1000.0, "pobi" to 2000.0))
        val player = game.players[0]

        game.deal(player = player, capacity = 1)
        player.currentDeck().toList() shouldHaveSize 1
    }

    test("존재하지 않는 플레이어에게 카드를 분배할 수 없다.") {
        val game = Game.from(listOf("catsbi" to 1000.0, "pobi" to 2000.0))
        val player = Gamer("invalidCatsbi")

        shouldThrow<IllegalArgumentException> {
            game.deal(player = player, capacity = 1)
        }
    }

    test("플레이어가 카드를 추가할 수 없는 경우 카드를 분배할 수 없다.") {
        val game = Game.from(listOf("catsbi" to 1000.0, "pobi" to 2000.0))
        val player = game.players[0]
        player.addCard(AceCard(symbol = SymbolType.DIAMOND))
        player.addCard(FaceCard(symbol = SymbolType.DIAMOND, faceType = FaceType.QUEEN))

        shouldThrow<IllegalArgumentException> {
            game.deal(player = player)
        }
    }
})
