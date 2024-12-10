package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class GameInitializerTest : FunSpec({
    val cardPicker = RandomCardPicker()
    val gameInitializer = GameInitializer(cardPicker)

    test("딜러는 초기화 시 카드 두 장을 가진다.") {
        // given, when
        val dealer = gameInitializer.initializeDealer()

        // then
        dealer.cards.all shouldHaveSize 2
    }

    test("플레이어는 초기화 시 카드 두 장을 가진다.") {
        // given
        val playerNames = listOf("dongyeon", "pobi", "jason")

        // when
        val players = gameInitializer.initializePlayers(playerNames)

        // then
        players shouldHaveSize playerNames.size
        players.forEach { player ->
            player.cards.all shouldHaveSize 2
        }
    }

    test("플레이어의 이름이 초기화에 반영된다.") {
        // given
        val playerNames = listOf("dongyeon", "pobi", "jason")

        // when
        val players = gameInitializer.initializePlayers(playerNames)

        // then
        players.map { it.name } shouldBe playerNames
    }
})
