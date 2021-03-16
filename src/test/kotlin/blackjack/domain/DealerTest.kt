package blackjack.domain

import blackjack.ui.model.PlayerDTO
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class DealerTest {

    @Test
    fun `player에게 두 장의 카드씩 준다`() {
        val players = listOf(Player("song"), Player("kim"))
        val dealer = Dealer(players, CardPack())

        val playerDTOs = dealer.giveTwoCardsToAllPlayers()
        Assertions.assertThat(playerDTOs).extracting("name").contains("song", "kim")
        Assertions.assertThat(playerDTOs[0].cards).hasSize(2)
        Assertions.assertThat(playerDTOs[1].cards).hasSize(2)

    }

    @Test
    fun `player에게 카드를 넘겨준다(아무도 받지 않는다)`() {
        val players = listOf(Player("song"), Player("kim"))
        val dealer = Dealer(players, CardPack())

        fun askKeepReceiving(name: String): Boolean {
            return false
        }

        fun doAfterReceive(playerDTO: PlayerDTO) {
            println("카드를 받았습니다.")
        }

        val playerDTOs = dealer.giveCardUntilStop(::askKeepReceiving, ::doAfterReceive)
        Assertions.assertThat(playerDTOs).extracting("name").contains("song", "kim")
        Assertions.assertThat(playerDTOs[0].cardNames).hasSize(0)
        Assertions.assertThat(playerDTOs[1].cardNames).hasSize(0)
    }

    @Test
    fun `player에게 카드를 넘겨준다(두 장 더 받는다)`() {
        val players = listOf(Player("song"), Player("kim"))
        val dealer = Dealer(players, CardPack())

        var askTimes = 0
        val blackJackResults = dealer.giveCardUntilStop({
            if (askTimes < 2) {
                askTimes += 1
                true
            } else {
                askTimes = 0
                false
            }
        }) { playerDTO -> println("${playerDTO.name} ${playerDTO.cards.joinToString(", ") { it.expression }}") }

        Assertions.assertThat(blackJackResults).extracting("name").contains("song", "kim")
        Assertions.assertThat(blackJackResults[0].cardNames).hasSize(2)
        Assertions.assertThat(blackJackResults[1].cardNames).hasSize(2)
    }
}
