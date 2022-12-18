package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class GameParticipatorsTest : StringSpec({
    lateinit var playerPobi: Player
    lateinit var playerCrong: Player

    beforeTest {
        playerPobi = Player.withName("pobi")
        playerCrong = Player.withName("crong")
    }

    "현재 차례의 플레이어가 반환됩니다" {
        val players = GameParticipators(listOf(playerPobi, playerCrong))

        players.currentParticipator() shouldBe playerPobi
        players.currentParticipator() shouldBe playerCrong
    }

    "플레이어가 게임을 그만둘 수 있습니다" {
        val players = GameParticipators(listOf(playerPobi, playerCrong))
        val currentPlayer = players.currentParticipator()

        players.quitGame(currentPlayer)

        players.currentParticipator() shouldBe playerCrong
        players.currentParticipator().shouldBeTypeOf<Dealer>()
        players.currentParticipator() shouldBe playerCrong
    }

    "딜러가 21점 초과시 게임은 종료됩니다" {
        val participators = GameParticipators(listOf(playerPobi, playerCrong))
        val dealer = participators.participators.single { it is Dealer }

        dealer.takeCards(
            Card(CardNumber.TEN, CardShape.CLOVER),
            Card(CardNumber.TEN, CardShape.CLOVER),
            Card(CardNumber.TWO, CardShape.CLOVER),
        )

        participators.isGameEnd() shouldBe true
    }

    "게임이 종료된 플레이어 리스트가 반환됩니다" {
        val participators = GameParticipators(listOf(playerPobi, playerCrong))
        val dealer = participators.participators.single { it is Dealer }

        participators.participators.forEach {
            it.takeCards(
                Card(CardNumber.TEN, CardShape.CLOVER),
                Card(CardNumber.ACE, CardShape.CLOVER),
            )
        }

        participators.finishParticipators() shouldContainAll listOf(playerPobi, playerCrong, dealer)
    }

    "딜러와 비교하여 플레이어 승리여부가 반환됩니다" {
        val participators = GameParticipators(listOf(playerPobi, playerCrong))
        val dealer = participators.participators.single { it is Dealer }

        dealer.takeCards(
            Card(CardNumber.ACE, CardShape.CLOVER)
        )
        playerCrong.takeCards(
            Card(CardNumber.ACE, CardShape.CLOVER),
            Card(CardNumber.TEN, CardShape.CLOVER),
        )
        playerPobi.takeCards(
            Card(CardNumber.TEN, CardShape.CLOVER),
        )

        participators.finishParticipators()
        val winners = participators.findWinner()
        winners shouldContainExactly mapOf(
            playerPobi to WinStatus.LOSE,
            playerCrong to WinStatus.WIN
        )
    }
})
