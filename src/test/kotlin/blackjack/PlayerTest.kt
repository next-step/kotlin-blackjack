package blackjack

import blackjack.domain.Player
import blackjack.domain.Players
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "Player 객체에 이름을 부여할 수 있다" {
        forAll(
            row("pobi"),
            row("jason")
        ) { name ->
            val player = Player(name)
            player.name shouldBe name
        }
    }

    "쉼표 기준으로 분리된 이름을 받아 Player들을 생성한다" {
        val nameList = listOf("pobi", "jason")
        val players = Players(nameList)

        nameList.all { name ->
            players.players.any { player ->
                player.name == name
            }
        } shouldBe true
    }
})
